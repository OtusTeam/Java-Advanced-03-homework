package otus.moryakovdv.task4.api.application;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import otus.moryakovdv.task4.service.WeatherService;


/**Обеспечивает API приложения.*/
@RestController
@Slf4j
public class Api {

	
	/**тестовый метод**/
	@RequestMapping(path = "/test")
	public String test() {
		log.debug("Test method called");
		return "It works!";
	}

	/***Запрос погодных условий*/
	@RequestMapping(path = "/weather")
	public String weatherConditions() {
		log.debug("Weather method called");
		
		WeatherService weatherService = new WeatherService();
		long result = weatherService.identifyAndSaveCurrentWeatherConditions();
		
		return String.format("saved with id "+result); 
		
	}
	
	
}
