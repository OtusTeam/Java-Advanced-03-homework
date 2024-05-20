package otus.moryakovdv.task4.service;

import otus.moryakovdv.task4.core.Core;
import otus.moryakovdv.task4.provider.WeatherProvider;
import otus.moryakovdv.task4.weatherdata.WeatherConditions;

/**Преобразовывает данные от модуля Provider в WeatherConditions и передает в CORE для записи в хранилище
 */
public class WeatherService {
	
	private Core coreModule = new Core();
	private WeatherProvider provider = new WeatherProvider();

	
	/**сохранение погодных условий
	 *  @param conditions - принятый от модуля Provider идентификатор погодных условий 
	 * 	@return - идентификатор созраненной сущности
	 * */
	private long saveCurrentWeatherConditions(int conditions) {
		WeatherConditions curConditions = WeatherConditions.UNDEFINED;
		if (conditions< WeatherConditions.values().length)
			curConditions = WeatherConditions.values()[conditions];
				
		return coreModule.saveCurrentConditions(curConditions);
		
		
	
	}
	
	/**метод для доступа к сервису извне*/
	public long identifyAndSaveCurrentWeatherConditions() {
		
		return saveCurrentWeatherConditions (provider.identifyCurrentWeather());
		
	}

}
