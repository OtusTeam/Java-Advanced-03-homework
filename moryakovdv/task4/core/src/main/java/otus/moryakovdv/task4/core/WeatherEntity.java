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
public class WeatherEntity {

	private long id;
	private long timestamp;
	private WeatherConditions currentConditions;
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeatherEntity other = (WeatherEntity) obj;
		return id == other.id;
	} 
}
