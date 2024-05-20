package otus.moryakovdv.task4.core;

import java.util.Objects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import otus.moryakovdv.task4.weatherdata.WeatherConditions;

/**Сущность - погодные условия. Сохраняется в общее хранилище по запросу от модуля Service*/

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of= {"id"})
public class WeatherEntity {

	private long id;
	private long timestamp;
	private WeatherConditions currentConditions;
	
}
