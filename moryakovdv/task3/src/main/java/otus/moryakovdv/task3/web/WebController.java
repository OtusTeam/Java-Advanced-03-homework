
package otus.moryakovdv.task3.web;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import otus.moryakovdv.task3.model.User;
import otus.moryakovdv.task3.repository.UsersCrudRepository;
import otus.moryakovdv.task3.service.LoginFailedException;
import otus.moryakovdv.task3.service.WebService;

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
