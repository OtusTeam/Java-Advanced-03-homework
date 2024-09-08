package otus.moryakovdv.task7.web;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
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
import otus.moryakovdv.task7.model.User;
import otus.moryakovdv.task7.repository.UsersCrudRepository;
import otus.moryakovdv.task7.service.LoginFailedException;
import otus.moryakovdv.task7.service.PasswordHasher;

/** Рест- Контроллер для пользователей */
@RestController
@Slf4j
public class UserController {

	@Autowired
	private UsersCrudRepository usersRepo;

	@Autowired
	private PasswordHasher passwordHasher;

	/**
	 * Метод аутентификации пользователя. Хранит зарегистрированных пользователей в
	 * Spring-репозитория, БД H2 in-memory
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
	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public User login(@RequestParam String userName, @RequestParam String passwd,
			@RequestParam(defaultValue = "false") boolean createIfAbsent)
			throws UnsupportedEncodingException, NoSuchAlgorithmException {

		String passHash = passwordHasher.hash(passwd);
		User user = usersRepo.findByUserNameAndPassword(userName, passHash).orElseGet(() -> {

			return createUser(userName, passwd, createIfAbsent);

		});
		user.setSessionId(UUID.randomUUID().toString());
		return user;

	}

	/** Количество пользователей в репозитории */
	@RequestMapping(value = "/userCount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public int countAllUsers() {
		AtomicInteger c = new AtomicInteger();
		usersRepo.findAll().forEach(u -> c.incrementAndGet());
		return c.get();
	}

	/**
	 * Сохраненение пользователя в репозитории, хеширует пароль перед записью
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	private User createUser(String userName, String password, boolean createIfAbsent) throws ResponseStatusException {
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

}
