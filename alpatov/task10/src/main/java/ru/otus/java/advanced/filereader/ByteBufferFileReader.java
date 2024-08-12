package ru.otus.java.advanced.filereader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class ByteBufferFileReader extends AbstractFileReader {

    @Override
    public String readFile(String fileName) {
        try (var fileChannel = (FileChannel) Files.newByteChannel(getPath(fileName), StandardOpenOption.READ)) {
            var byteBuffer = ByteBuffer.allocate((int) fileChannel.size());
            fileChannel.read(byteBuffer);
            byteBuffer.flip();
            return String.valueOf(StandardCharsets.UTF_8.decode(byteBuffer));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}