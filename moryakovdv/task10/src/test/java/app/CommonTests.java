package app;

import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import otus.moryakovdv.task10.ByteBufferFileContent;
import otus.moryakovdv.task10.MappedByteBufferFileContent;

@TestMethodOrder(OrderAnnotation.class)
class CommonTests {

	String path = "testfiles/Readme.md";
	
	@Test
	/**тест релизации на ByteBufer. Файл из ресурсов*/
	void testByteBuffer() throws Exception {
		
		ByteBufferFileContent bbc = new ByteBufferFileContent();
		ByteBuffer content = bbc.readFile(path);
		bbc.put(path, content);
		
		bbc.printContent(bbc.get(path));
	
		
	}
	
	@Test
	/**тест релизации на MappedByteBufer. Файл из ресурсов*/
	void testMappedByteBuffer() throws Exception {
		
		MappedByteBufferFileContent mbbc = new MappedByteBufferFileContent();
		MappedByteBuffer content = mbbc.readFile(path);
		mbbc.put(path, content);
		
		mbbc.printContent(mbbc.get(path));
	}
}
