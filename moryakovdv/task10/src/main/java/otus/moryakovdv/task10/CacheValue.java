package otus.moryakovdv.task10;

import java.nio.ByteBuffer;

/**Интерфейс кешированных значений */
public interface CacheValue<C> {
	
	/**Содержимое **/
	public ByteBuffer getContent();
}
