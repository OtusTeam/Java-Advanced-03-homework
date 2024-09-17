package otus.moryakovdv.task10;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public abstract class OffHeapContent {

	/***
	 * Чтение файла чанками через Buffer
	 * 
	 * @param filePath   - путь к файлу
	 * @param bufferSize - размер чанка - буффера чтения, байт
	 * @param offset     - смещение от начала, байт
	 * 
	 * @return - код результата выполнения (short). -1 = ошибка
	 */
	public abstract short readFile(Path path, int bufferSize, int offset);

	/** Получение пути к файлу в ФС или из ресурсов через класслоадер */
	public Path getFilePath(String fileName) throws Exception {
		Path path = Paths.get(fileName);
		if (!Files.exists(path)) {

			ClassLoader classLoader = getClass().getClassLoader();
			return Paths.get(classLoader.getResource(fileName).getPath());
		}
		else
			return path;
	}

}
