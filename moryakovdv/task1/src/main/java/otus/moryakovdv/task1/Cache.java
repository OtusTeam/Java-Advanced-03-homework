package otus.moryakovdv.task1;

/**Интерфейс описания абстрактного кеша**/
public interface Cache<K, V> {
	
	/**Поместить в кеш**/
	void put(K key, V value);
	
	/**Запросить из кеша**/
	V get(K key);
	
	/**Удалить из кеша**/
	V remove(K key);
	
	/**Вывести содержимое кеша**/
	void traverse();
	
	/**Старт потока очистки пустых ссылок**/
	void startReaper();

	/**останов потока очистки**/
	void stopReaper();
	
	/**Вывод количества хитов**/
	int cacheHitCount();

}
