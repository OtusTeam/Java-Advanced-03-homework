package task10.fileUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class CustomFileReader {

    public String readFile(String path) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            return new String(bytes, Charset.defaultCharset());
        } catch (IOException e) {
            throw new IllegalStateException("Couldn't read file");
        }
    }

    Path getFileURIFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        return Paths.get(classLoader.getResource(fileName).getPath());
    }

    public MappedByteBuffer readMappedByteBufferFromFile(String fileName) throws Exception {
        MappedByteBuffer mappedByteBuffer = null;
        Path pathToRead = getFileURIFromResources(fileName);
        try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(pathToRead, EnumSet.of(StandardOpenOption.READ))) {
            mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
        }
        return mappedByteBuffer;
    }

    public ByteBuffer readByteBufferFromFile(String fileName) {
        Path pathToRead = getFileURIFromResources(fileName);
        ByteBuffer buffer = null;
        try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(pathToRead, EnumSet.of(StandardOpenOption.READ))) {
            long fileSize = fileChannel.size();
            buffer = ByteBuffer.allocate((int) fileSize);
            fileChannel.read(buffer);
            buffer.flip();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }
}
