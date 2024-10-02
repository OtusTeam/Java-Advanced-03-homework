package otus.moryakovdv.task13.web;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import otus.moryakovdv.task13.service.WebService;

/** Контроллер, обеспечивающий вызов веб-методов **/
@RestController
@Slf4j
public class WebController {

	private AtomicLong requestsCount = new AtomicLong();

	@Autowired
	private WebService webService;

	

	/** Вывод сообщения "помощь" **/
	@RequestMapping("/")
	public String base() {
		return this.webService.getHelpMessage();
	}

	/** Тестовый метод */
	@RequestMapping("/test")
	public String testMethod() {
		return this.webService.getTestMessage();
	}
	
	/** Метод HELLO*/
	@RequestMapping(value= "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String helloMethod() {
		return "Hello there!";
	}

	/** веб-метод создания контента в кеше для переданного SessionID 
	 * @throws InterruptedException */
	@RequestMapping(value = "/process", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> processRequest(@RequestParam String sessionId) throws IOException, InterruptedException {
		long requests = requestsCount.incrementAndGet();
		long hits = webService.processContent(sessionId);

		String result = String.format("{Processed queries: %d hits:%d}", requests, hits);
		log.debug(result);

		return ResponseEntity.ok(String.format("{\"Processed queries\":%d}", requests));

	}

}
