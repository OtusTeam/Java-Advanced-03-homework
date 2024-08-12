package ru.otus.java.advanced.filereader;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class MappedByteBufferFileReader extends AbstractFileReader {

    @Override
    public String readFile(String fileName) {
        try (var fileChannel = (FileChannel) Files.newByteChannel(getPath(fileName), StandardOpenOption.READ)) {
            var mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
            return String.valueOf(StandardCharsets.UTF_8.decode(mappedByteBuffer));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
