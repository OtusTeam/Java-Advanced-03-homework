package otus.moryakovdv.task10;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.WeakHashMap;

/** Кеш cодержимого файлов с харнением в ByteBuffer **/
public class ByteBufferFileContent implements Cache<String, ByteBuffer> {

	private WeakHashMap<String, ByteBuffer> fileCache = new WeakHashMap<>();

	@Override
	public void put(String key, ByteBuffer value) {

		fileCache.put(key, value);
	}

	@Override
	public ByteBuffer get(String key) {

		return fileCache.get(key);
	}

	@Override
	public ByteBuffer remove(String key) {

		return fileCache.remove(key);
	}

	@Override
	public int cacheHitCount() {

		return fileCache.size();
	}

	@Override
	public ByteBuffer readFile(String name) throws Exception {

		Path filePath = getFilePath(name);

		try (SeekableByteChannel byteChannel = Files.newByteChannel(filePath, EnumSet.of(StandardOpenOption.READ))) {

			ByteBuffer buf = ByteBuffer.allocateDirect((int) byteChannel.size());

			byteChannel.read(buf);

			return buf;

		} catch (Exception e) {
			throw new IOException(e);
		}

	}

	@Override
	public void printContent(ByteBuffer content) throws Exception {

		System.out.println("===BYTEBUFFER BEGIN OF FILE ===");
		content.flip();
		CharBuffer cb = Charset.forName("UTF-8").decode(content);
		while (cb.hasRemaining()) {
			System.out.print(cb.get());
		}
		System.out.println("===BYTEBUFFER END OF FILE ===");

	}

}
