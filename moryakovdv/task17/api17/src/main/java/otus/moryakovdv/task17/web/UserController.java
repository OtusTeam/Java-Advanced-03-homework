package otus.moryakovdv.task17.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import otus.moryakovdv.task17.model.User;

/** Рест- Контроллер для пользователей */
@RestController
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

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

	ExecutorService processContentExecutor = Executors.newFixedThreadPool(4, new ThreadFactory() {

		AtomicInteger c = new AtomicInteger();

		@Override
		public Thread newThread(Runnable arg0) {
			Thread t = new Thread(arg0, "processing-thread-" + c.incrementAndGet());
			return t;
		}
	});

	@RequestMapping(value = "/getUserAgeRPM", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer getUserAgeRPM(@RequestParam int id) {
		log.info("getUserAgeRPM called");
		return userService.findUserAgeWithRPM(id);

	}

	@RequestMapping(value = "/getUserAgeRPS", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer getUserAgeRPS(@RequestParam int id) {
		log.info("getUserAgeRPS called");
		return userService.findUserAgeWithRPS(id);

	}

	@RequestMapping(value = "/getUserAgeCB", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer getUserAgeCB(@RequestParam int id) throws Exception {
		log.info("getUserAgeCB called");
		return userService.findUserAgeWithCB(id);

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public User login(@RequestParam String userName, @RequestParam String passwd,
			@RequestParam(defaultValue = "false") boolean createIfAbsent)
			throws NoSuchAlgorithmException, InterruptedException, ExecutionException, IOException {

		return userService.doLogin(userName, passwd, createIfAbsent);

	}

}
