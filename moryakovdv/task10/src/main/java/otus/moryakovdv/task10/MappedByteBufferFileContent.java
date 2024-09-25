package otus.moryakovdv.task10;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.WeakHashMap;

/** Реализация OffHeapContent через MappedByteBuffer */
public class MappedByteBufferFileContent implements Cache<String, MappedByteBuffer> {

	private WeakHashMap<String, MappedByteBuffer> fileCache = new WeakHashMap<>();

	@Override
	public void put(String key, MappedByteBuffer value) {

		fileCache.put(key, value);
	}

	@Override
	public MappedByteBuffer get(String key) {

		return fileCache.get(key);
	}

	@Override
	public MappedByteBuffer remove(String key) {

		return fileCache.remove(key);
	}

	@Override
	public int cacheHitCount() {

		return fileCache.size();
	}

	@Override
	public MappedByteBuffer readFile(String name) throws Exception {

		Path filePath = getFilePath(name);

		try (FileChannel byteChannel = (FileChannel) Files.newByteChannel(filePath,
				EnumSet.of(StandardOpenOption.READ))) {

			return byteChannel.map(FileChannel.MapMode.READ_ONLY, 0, byteChannel.size());

		} catch (Exception e) {
			throw new IOException(e);
		}

	}

	@Override
	public void printContent(MappedByteBuffer content) throws Exception {
		System.out.println("===MAPPEDBYTEBUFFER BEGIN OF FILE ===");

		CharBuffer cb = Charset.forName("UTF-8").decode(content);
		while (cb.hasRemaining()) {
			System.out.print(cb.get());
		}
		System.out.println("===MAPPEDBYTEBUFFER END OF FILE ===");

	}

}
