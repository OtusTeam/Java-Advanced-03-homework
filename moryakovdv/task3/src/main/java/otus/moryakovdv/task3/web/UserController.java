package otus.moryakovdv.task3.web;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

import otus.moryakovdv.task3.model.User;
import otus.moryakovdv.task3.repository.UsersCrudRepository;
import otus.moryakovdv.task3.service.LoginFailedException;
/**Рест- Контроллер для пользователей*/
@RestController
public class UserController {

	private UsersCrudRepository userRepository;

	@Autowired
	public UserController(UsersCrudRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/user/all")
	public List<User> allUsers() {
		LinkedList<User> result = new LinkedList<User>();
		userRepository.findAll().forEach(u -> result.add(u));
		return result;
	}
	
	/**Метод аутентификации пользователя.
	 * Хранит зарегистрированных пользователей в Spring-репозитория, БД H2 in-memory
	 * @param userName - login пользователя,String
	 * @param passwd -  пароль пользователя,String
	 * @param createIfAbsent - флаг создания нового пользвоателя, boolean, default = false 
	 * @return ResponseEntity<User> - json-описание созданного или залогиненного User
	 * @throws LoginFailedException - если юзер не найден и флаг создания не поднят
	 * */
	@RequestMapping(value="/user/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> login(@RequestParam String userName, @RequestParam String passwd, @RequestParam(defaultValue =  "false") boolean createIfAbsent )  {

		Optional<User> existingUser = userRepository.findByUserNameAndPassword(userName, passwd);
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
		return userRepository.save(newUser);

	}
}