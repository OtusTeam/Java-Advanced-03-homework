package otus.moryakovdv.task4.provider;

import java.util.Random;

/**Класс определения текущих погодных условий*/
public class WeatherProvider {
	
	/**возвращает id погодных условий 
	 * @return id - Random(0-5), используется в модуле */
	public int identifyCurrentWeather() {
		
		Random rnd = new Random();
		return rnd.nextInt(0, 5);
		
	}

}
