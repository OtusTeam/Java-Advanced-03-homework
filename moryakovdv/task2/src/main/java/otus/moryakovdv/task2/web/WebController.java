
package otus.moryakovdv.task2.web;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import otus.moryakovdv.task2.model.User;
import otus.moryakovdv.task2.repository.UsersCrudRepository;
import otus.moryakovdv.task2.service.LoginFailedException;
import otus.moryakovdv.task2.service.WebService;

/**Контроллер, обеспечивающий вызов веб-методов**/
@Controller
public class WebController {

	private AtomicLong requestsCount = new AtomicLong();
	
	@Autowired
	private WebService webService;

	@Autowired
	private UsersCrudRepository usersRepo;

	/**Вывод сообщения "помощь" **/
	@RequestMapping("/")
	public @ResponseBody String base() {
		return this.webService.getHelpMessage();
	}

	/**Тестовый метод*/
	@RequestMapping("/test")
	public @ResponseBody String testMethod() {
		return this.webService.getTestMessage();
	}

	/**Метод аутентификации пользователя.
	 * Хранит зарегистрированных пользователей в Spring-репозитория, БД H2 in-memory
	 * @param userName - login пользователя,String
	 * @param passwd -  пароль пользователя,String
	 * @param createIfAbsent - флаг создания нового пользвоателя, boolean, default = false 
	 * @return ResponseEntity<User> - json-описание созданного или залогиненного User
	 * @throws LoginFailedException - если юзер не найден и флаг создания не поднят
	 * */
	@RequestMapping(value="/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> login(@RequestParam String userName, @RequestParam String passwd, @RequestParam(defaultValue =  "false") boolean createIfAbsent )  {

		Optional<User> existingUser = usersRepo.findByUserNameAndPassword(userName, passwd);
		if (existingUser.isPresent()) {
			User u = existingUser.get();
			u.setSessionId(UUID.randomUUID().toString());
			return  ResponseEntity.ok(u);
		} 
		else 
			if (createIfAbsent==true) {
				
				User createdUser = createUser(userName, passwd);
				createdUser.setSessionId(UUID.randomUUID().toString());
				return ResponseEntity.ok(createdUser);
			}
		
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
		
	}
	
	/**Сохраненение пользователя в репозитории*/
	private User createUser(String userName, String password) {

		User newUser = new User(userName, password);
		return usersRepo.save(newUser);

	}
	 
	/**веб-метод создания контента в кеше для переданного SessionID*/
	 @RequestMapping(value="/process", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) 
	 public ResponseEntity<String> processRequest(@RequestParam String sessionId) throws IOException {
		 long requests =  requestsCount.incrementAndGet();
		 long hits = webService.processContent(sessionId);
		 
		String result=  String.format("{Processed queries: %d hits:%d}",requests,hits);
		System.out.println(result);
		 
		return ResponseEntity.ok(String.format("{\"Processed queries\":%d}",requests));
		  
	 }
	 
}
