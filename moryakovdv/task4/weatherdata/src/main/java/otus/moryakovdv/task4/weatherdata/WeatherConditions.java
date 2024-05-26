package otus.moryakovdv.task4.weatherdata;


/**погодные условия*/
public enum WeatherConditions {

	GOOD, FOGGY, RAINY, CLOUDY, STORMY, UNDEFINED;
	
	@Override
	public String toString() {
		switch (this) {
		case GOOD:
			return "";
		case FOGGY:
			return "Туман";
		case RAINY:
			return "Осадки (дождь)";
		case CLOUDY:
			return "Облачно";
		case STORMY:
			return "Шторм, грозы и разрушения";
		case UNDEFINED: default:
				return "Не определено";
		
		}
	}
}
