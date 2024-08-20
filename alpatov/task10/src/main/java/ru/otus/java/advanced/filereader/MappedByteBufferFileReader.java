package ru.otus.java.advanced.filereader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class MappedByteBufferFileReader extends AbstractFileReader {

    @Override
    public ByteBuffer readFile(String fileName) {
        try (var fileChannel = (FileChannel) Files.newByteChannel(getPath(fileName), StandardOpenOption.READ)) {
            return fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
