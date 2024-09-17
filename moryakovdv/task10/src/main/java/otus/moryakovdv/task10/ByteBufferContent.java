package otus.moryakovdv.task10;

import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

/**Реализация OffHeapContent через ByteBuffer */
public class ByteBufferContent extends OffHeapContent {

	@Override
	public short readFile(Path path, int bufferSize, int offset) {
		
		int bytesCount = 0;
		try (SeekableByteChannel byteChannel = Files.newByteChannel(path, EnumSet.of(StandardOpenOption.READ))) {

			byteChannel.position(offset);

			ByteBuffer buffer = ByteBuffer.allocateDirect(bufferSize);

			do {
				bytesCount = byteChannel.read(buffer);
				if (bytesCount != -1) {
					buffer.rewind();
					for (int i = 0; i < bytesCount; i++) {
						System.out.print((char) buffer.get());
					}
				}
			} while (bytesCount != -1);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
		return 0;

	}

}
