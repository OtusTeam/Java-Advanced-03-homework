package otus.moryakovdv.task3.web;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import lombok.extern.slf4j.Slf4j;
import otus.moryakovdv.task3.model.User;
import otus.moryakovdv.task3.repository.UsersCrudRepository;
import otus.moryakovdv.task3.service.LoginFailedException;
import otus.moryakovdv.task3.service.WebService;

/**Рест- Контроллер для пользователей*/
@RestController
@Slf4j
public class UserController {

	private AtomicLong requestsCount = new AtomicLong();

	@Autowired
	private WebService webService;

	@Autowired
	private UsersCrudRepository usersRepo;

	



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
	 * @throws LoginFailedException - если юзер не найден и флаг создания не поднят
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public User login(@RequestParam String userName, @RequestParam String passwd,
			@RequestParam(defaultValue = "false") boolean createIfAbsent) {

		
		User user = usersRepo.findByUserNameAndPassword(userName, passwd).orElseGet(()->createUser(userName, passwd, createIfAbsent));
		user.setSessionId(UUID.randomUUID().toString());
		return user;
		
		
		
	}
	
	
	
	
	/** Количество пользователей в репозитории */
	@RequestMapping(value = "/userCount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public int countAllUsers() {
		AtomicInteger c = new AtomicInteger();
		usersRepo.findAll().forEach(u->c.incrementAndGet());
		return c.get();
	}

	/** Сохраненение пользователя в репозитории */
	private User createUser(String userName, String password, boolean createIfAbsent) throws ResponseStatusException {
		if (createIfAbsent) {
			User newUser = new User(userName, password);
			return usersRepo.save(newUser);
		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");

	}

	/** веб-метод создания контента в кеше для переданного SessionID */
	@RequestMapping(value = "/process", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String processRequest(@RequestParam String sessionId) throws IOException {
		long requests = requestsCount.incrementAndGet();
		long hits = webService.processContent(sessionId);


		String result = String.format("{Processed queries: %d hits:%d}", requests, hits);
		log.debug(result);

		return String.format("{\"Processed queries\":%d}", requests);

	}

}
