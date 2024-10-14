package otus.moryakovdv.task17.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import otus.moryakovdv.task17.model.User;
import otus.moryakovdv.task17.repository.UsersCrudRepository;
import otus.moryakovdv.task17.service.PasswordHasher;
import otus.moryakovdv.task17.service.WebService;

@Service
@Slf4j
/**Сервис для работы с USER*/
public class UserService {

	@Autowired
	private WebService wService;

	@Autowired
	private UsersCrudRepository usersRepo;

	@Autowired
	private PasswordHasher passwordHasher;
	

	/**метод, возвращающий возраст пользователя. Защищен от перегрузки с RateLimit (N запросов в минуту)*/
	@RateLimiter(name = "RPM")
	public int findUserAgeWithRPM(int id) {
		log.info("findUserAgeWithRPM called");
		return findUserAge(id);
	}

	/**метод, возвращающий возраст пользователя. Защищен от перегрузки с RateLimit (N запросов в секунду)*/
	@RateLimiter(name = "RPS") 
	public int findUserAgeWithRPS(int id) {
		log.info("findUserAgeWithRPS called");

		return findUserAge(id);

	}
	
	/**метод, возвращающий возраст пользователя. Защищен от перегрузки Circuit breaker
	 * @throws Exception */
	@CircuitBreaker(name = "CBR")
	public int findUserAgeWithCB(int id) throws Exception {
		
		Random  failureRnd = new Random();
		if (failureRnd.ints(100).findAny().getAsInt()%2==0) {
			
			throw new java.lang.Exception("Random failure in findUserAgeWithCB");
			
		}
			
		
		log.info("findUserAgeWithCB called");
		return findUserAge(id);

	}

	/**backend-метод, возвращающий возраст пользователя. Сам по себе не защищен от перегрузки, не планируется вызывать напрямую*/
	private int findUserAge(int id) {

		log.info("Pure findUserAge method called");

		User user = usersRepo.findById(id).orElseGet(() -> {
			String uuid = UUID.randomUUID().toString();
			Random rnd = new Random();

			User newUser = createUser("mockUserName", "mockPassword", true);

			newUser.setSessionId(uuid);
			int mockAge = rnd.ints(5, 65).findAny().getAsInt();
			newUser.setUserAge(mockAge);

			usersRepo.save(newUser);

			return newUser;

		});

		return user.getUserAge();

	}

	/**
	 * Сохраненение пользователя в репозитории, хеширует пароль перед записью
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public User createUser(String userName, String password, boolean createIfAbsent) throws ResponseStatusException {
		if (createIfAbsent) {

			try {
				password = passwordHasher.hash(password);
				User newUser = new User(userName, password);
				return usersRepo.save(newUser);
			} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Password hash exception");
			}

		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");

	}

	public User doLogin(String userName, String passwd, boolean createIfAbsent)
			throws IOException, NoSuchAlgorithmException {

		String passHash = passwordHasher.hash(passwd);

		User user = usersRepo.findByUserNameAndPassword(userName, passHash).orElseGet(() -> {

			return createUser(userName, passwd, createIfAbsent);

		});
		String uuid = UUID.randomUUID().toString();

		user.setSessionId(uuid);
		wService.processContent(uuid);
		return user;

	}

	

}
