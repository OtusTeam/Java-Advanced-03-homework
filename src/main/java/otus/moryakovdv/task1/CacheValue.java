package otus.moryakovdv.task1;

import java.util.List;

/**Интерфейс кешированных значений */
public interface CacheValue<C> {
	
	/**Содержимое **/
	public List<C> getContent();
}
