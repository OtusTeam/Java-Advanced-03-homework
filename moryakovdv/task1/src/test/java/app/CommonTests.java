package app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import otus.moryakovdv.task1.Cache;
import otus.moryakovdv.task1.FileContent;
import otus.moryakovdv.task1.SoftReferenceValueCache;
import otus.moryakovdv.task1.WeakReferenceValueCache;

@TestMethodOrder(OrderAnnotation.class)
class CommonTests {

	private static Cache<String, FileContent> weakCache;
	private static Cache<String, FileContent> softCache;

	private static final String fileName1 = "Names.txt";
	private static final String fileName2 = "Address.txt";

	/**Иницилизация при старте тестов*/
	@BeforeAll
	static void before() {
		weakCache = new WeakReferenceValueCache();
		softCache = new SoftReferenceValueCache();
	}
	
	/***Проверка инициализации **/
	@Test
	@Order(1)
	void testEnv() {
		
		System.out.println("1. Test env init");
		assertNotNull(weakCache);
		assertNotNull(softCache);

	}
	
	/****Тестирование weak-кеша**/
	@Test
	@Order(2)
	void testWeakCache() throws FileNotFoundException, IOException {
		testCache(weakCache, fileName1, 2);
		
	}
	
	
	
	
	@Test
	@Order(3)
	/****Тестирование soft-кеша**/

	void testSoftCache() throws FileNotFoundException, IOException {

		
		testCache(softCache, fileName2, 3);
	}
	
	@Test
	@Order(4)
	/****Тестирование сработки GC**/
	void testGC() {
		
		System.out.println("test GC call");
		System.out.println("BEFORE GC");
		System.out.println("WEAK:");

		weakCache.traverse();
		System.out.println("SOFT:");
		softCache.traverse();
		
		System.gc();
		
		System.out.println("AFTER GC");

		System.out.println("WEAK:");
		weakCache.traverse();
		
		System.out.println("SOFT:");
		softCache.traverse();
		
	}
	
	/***Заносит и запрашивает занчение в кеш нужное число раз. Проверяет счетчик хитов*/
	private void testCache(Cache<String,FileContent> cache, String fileName, int hits) throws FileNotFoundException, IOException {
		
		System.out.println("Testing "+cache.getClass().getSimpleName());
		FileContent fc =  cache.get(fileName);
		assertNull(fc);
		
		fc = FileContent.valueOf("testfiles",fileName);
		cache.put(fileName, fc);
		
		for(int i=0;i<hits;i++) {
			FileContent fcGot = cache.get(fileName);
			assertNotNull(fcGot.getContent());
		}
		assertEquals(cache.cacheHitCount(), hits);
		
		softCache.traverse();
	}

}
