package app;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import otus.moryakovdv.task10.ByteBufferContent;
import otus.moryakovdv.task10.MappedByteBufferContent;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class CommonTests {

	String path = "testfiles/Readme.md";
	
	@Test
	/**тест релизации на ByteBufer. Файл из ресурсов*/
	void testByteBuffer() throws Exception {
		
		
		ByteBufferContent	content = new ByteBufferContent();
		Path p = content.getFilePath(path);
		short result = content.readFile(p, 512, 0);
		assertEquals(0, result);
	}
	
	@Test
	/**тест релизации на MappedByteBufer. Файл из ресурсов*/
	void testMappedByteBuffer() throws Exception {
		
		MappedByteBufferContent	content = new MappedByteBufferContent();
		Path p = content.getFilePath(path);
		short result = content.readFile(p, 512, 0);
		assertEquals(0, result);
		
	}
}
