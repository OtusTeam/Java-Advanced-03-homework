package otus.moryakovdv.task15.web;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import otus.moryakovdv.task15.model.User;

@RestController
@RequestMapping("/user")
@Slf4j
public class Task15Controller {
	
	private AtomicInteger c = new AtomicInteger();

	
	@RequestMapping(value = "/test",produces = MediaType.APPLICATION_JSON_VALUE)
	public String test() {

		log.debug("Test method called User created");
		return "Test user method works!";
	

	}
	
	
	/** Добавление пользователя в репозиторий */
	@Operation(description = "Добавление пользователя в репозиторий ", summary = "Вкратце: добавление")
	@RequestMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Long addUser() {

		log.debug("Dummy User created");
		return getDummyUser().getId();
	

	}
	
	/** Изменение пользователя */
	@Operation(description = "Получение пользователя из репозиторий ", summary = "Вкратце: Получение")
	@RequestMapping(value = "/get",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, params = {"id"})
	public User getUser() {

		
		User userToEdit =  getDummyUser();
		UUID sessionId = UUID.randomUUID(); 
		
		userToEdit.setSessionId(sessionId.toString());
		log.debug("Dummy User value sent");

		return userToEdit;

	}
	
	/** Изменение пользователя */
	@Operation(description = "Изменение пользователя в репозитории ", summary = "Вкратце: Изменение")
	@RequestMapping(value = "/edit",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, params = {"id"})
	public String editUser() {

		
		User userToEdit =  getDummyUser();
		UUID sessionId = UUID.randomUUID(); 
		
		userToEdit.setSessionId(sessionId.toString());
		log.debug("Dummy User sessionId set to {}",userToEdit.getSessionId());

		return userToEdit.getSessionId();

	}
	
	@Operation(description = "А это метод не для посторонниз", summary = "Вкратце: Не влезай! Убьет!")
	private User getDummyUser() {
		
		UUID login = UUID.randomUUID(); 
		UUID password = UUID.randomUUID(); 

		
		User dummyUser = new User(login.toString(), password.toString());
		dummyUser.setId(c.longValue());
		
		return dummyUser;
	}

}
