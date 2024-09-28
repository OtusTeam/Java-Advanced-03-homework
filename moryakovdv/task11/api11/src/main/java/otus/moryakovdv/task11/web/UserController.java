package otus.moryakovdv.task11.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;
import otus.moryakovdv.task11.model.UserOtus;
import otus.moryakovdv.task11.repository.FluxUserRepository;
import otus.moryakovdv.task11.service.LoginFailedException;
import otus.moryakovdv.task11.service.PasswordHasher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/** Рест- Контроллер для пользователей */
@RestController
@Slf4j
public class UserController {

	@Autowired
	private FluxUserRepository usersRepo;

	@Autowired
	private PasswordHasher passwordHasher;

	

	/**
	 * Метод аутентификации пользователя. Хранит зарегистрированных пользователей в
	 * Spring-репозитория, БД H2 
	 * 
	 * @param userName       - login пользователя,String
	 * @param passwd         - пароль пользователя,String
	 * @param createIfAbsent - флаг создания нового пользвоателя, boolean, default =
	 *                       false
	 * @return ResponseEntity<User> - json-описание созданного или залогиненного
	 *         User
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 * @throws LoginFailedException         - если юзер не найден и флаг создания не
	 *                                      поднят
	 */

	ExecutorService processContentExecutor = Executors.newFixedThreadPool(4,new ThreadFactory() {
		
		AtomicInteger c = new AtomicInteger();
		
		@Override
		public Thread newThread(Runnable arg0) {
			Thread t = new Thread(arg0, "processing-thread-"+c.incrementAndGet());
			return t;
		}
	});

	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<UserOtus> login(@RequestParam String userName, @RequestParam String passwd,
			@RequestParam(defaultValue = "false") boolean createIfAbsent)
			throws NoSuchAlgorithmException, InterruptedException, ExecutionException, IOException {

		log.debug("Login called {} {}",userName, passwd);
		
		String passHash = passwordHasher.hash(passwd);

		Flux<UserOtus> user = usersRepo.findByUserNameAndPassword(userName, passHash).defaultIfEmpty(createUser(userName, passwd,true));
				
		return user.single();

	}
		

	
	/**
	 * Сохраненение пользователя в репозитории, хеширует пароль перед записью
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	private UserOtus createUser(String userName, String password, boolean createIfAbsent) throws ResponseStatusException {
		if (createIfAbsent) {

			try {
				password = passwordHasher.hash(password);
				UserOtus newUser = new UserOtus(userName, password);
				
				String initialSessionUuid = UUID.randomUUID().toString();

				newUser.setSessionId(initialSessionUuid);
				usersRepo.save(newUser);
				return newUser;
			} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Password hash exception");
			}

		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");

	}

}
