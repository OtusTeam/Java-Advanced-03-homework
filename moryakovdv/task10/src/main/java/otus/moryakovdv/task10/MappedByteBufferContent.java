package otus.moryakovdv.task10;

import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

/**Реализация OffHeapContent через MappedByteBuffer */
public class MappedByteBufferContent extends OffHeapContent {

	/**Использует MappedByteBuffer с размещением чанка непосредственно в памяти*/
	@Override
	public short readFile(Path path, int bufferSize, int offset) {
		CharBuffer charBuffer = null;
		long fileSize=0;
		try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(path, EnumSet.of(StandardOpenOption.READ))) {

			MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, offset, bufferSize);
			fileSize = fileChannel.size();
			while (mappedByteBuffer != null && offset<=fileSize) {

				
				mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, offset, Math.min(bufferSize, fileSize-offset));
				
				if (mappedByteBuffer != null) {
					charBuffer = Charset.forName("UTF-8").decode(mappedByteBuffer);
					System.out.println(charBuffer);
				}
				offset += bufferSize;
			}
		} catch (IOException e) {

			e.printStackTrace();
			return -1;
		}
		
		return 0;
	}

}
