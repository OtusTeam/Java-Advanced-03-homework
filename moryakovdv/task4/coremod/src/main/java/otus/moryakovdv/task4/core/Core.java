package otus.moryakovdv.task4.core;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import otus.moryakovdv.task4.weatherdata.WeatherConditions;

/***Класс имитации сохранения результата. Пишет в коллекцию сущность в БД с автоинкрементным идентификатором.
 * Использует доступ к логгеру из модуля app
 * */
public class Core {
	
	private Map<Long, WeatherEntity> storage = new HashMap<>();
	
	private AtomicLong sequence = new AtomicLong(1);
	
	/**Создать WeatherEntity и положить в харнилище
	 * @param conditions - передаваемые условия
	 * @return id сохраненной сущности
	 * */
	public long saveCurrentConditions(WeatherConditions conditions) {
		
		WeatherEntity entity = new WeatherEntity();
		entity.setTimestamp(Instant.now().toEpochMilli());
		entity.setCurrentConditions(conditions);
		entity.setId(generateId());
		return saveEntity(entity).getId();
		
		
	}
	
	/**Сохранение в хранилище по запросу от модуля Service*/
	private WeatherEntity saveEntity(WeatherEntity entity) {
		storage.put(entity.getId(), entity);
		return entity;
		
	}
	
	/**идентификатор*/
	private long generateId() {
		return sequence.incrementAndGet();
	}
	
	
	
}
