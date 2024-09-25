package otus.moryakovdv.task10;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/** Интерфейс описания абстрактного кеша **/
public interface Cache<K, V extends ByteBuffer> {

	/** Поместить в кеш **/
	void put(K key, V value);

	/** Запросить из кеша **/
	V get(K key);

	/** Удалить из кеша **/
	V remove(K key);

	/** Вывод количества хитов **/
	int cacheHitCount();

	/**Загрузка содержимого*/
	V readFile(String path) throws Exception;

	/**распечатка содержимого */
	 void printContent(V content) throws Exception;

	/**общая реализация получения пути по имени файла*/
	default Path getFilePath(String fileName) throws Exception {
		Path path = Paths.get(fileName);
		if (!Files.exists(path)) {

			ClassLoader classLoader = getClass().getClassLoader();
			return Paths.get(classLoader.getResource(fileName).getPath());
		} else
			return path;
	}

}
