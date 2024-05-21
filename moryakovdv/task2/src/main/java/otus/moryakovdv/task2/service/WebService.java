
package otus.moryakovdv.task2.service;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/***Класс релизации бизнес-логики веб-сервиса**/
@Component
public class WebService {

	//кеш
	//private Map<Integer, byte[]> cache = new HashMap<>();
	  private Map<Integer, byte[]> cache = new WeakHashMap<>();

	
	
	//Попадания в кеш
	private AtomicLong hits = new AtomicLong();

	@Value(value = "${app.help.message:<h1>USAGE:</h1>}")
	private String helpMessage;

	@Value(value = "${app.test.message:It works!}")
	private String testMessage;

	/**Вывод тестового сообщения*/
	public String getTestMessage() {
		return testMessage;
	}

	/***Сообщение об использовании*/
	public String getHelpMessage() {
		return helpMessage;

	}

	/**Создание контента для запроса, заполнение кеша**/
	public long processContent(String callerSessionId) throws IOException {
		Random rnd = new Random();
		Integer key = rnd.nextInt(1, 30000);

		byte[] content = generateContent();
		byte[] sessionIdTail = new byte[] {};

		if (callerSessionId != null)
			sessionIdTail = callerSessionId.getBytes("UTF-8");
		ByteBuffer bf = ByteBuffer.allocateDirect(content.length + 8 + sessionIdTail.length);
		
		bf.put(content);
		bf.putInt(key);
		bf.put(sessionIdTail);

		
		byte[] hit = cache.putIfAbsent(key, content);
		if (hit!=null) {
			hits.incrementAndGet();
		}
		
		
	

		return hits.get();
	}

	/**Генерация контента по содержимому файла LICENSE*/
	private byte[] generateContent() throws IOException {

		return this.getClass().getResourceAsStream("/LICENSE").readAllBytes();

	}

}
