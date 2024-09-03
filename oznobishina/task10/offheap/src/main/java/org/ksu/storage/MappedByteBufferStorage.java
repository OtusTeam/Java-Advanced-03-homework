package org.ksu.storage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;


public class MappedByteBufferStorage {
    private MappedByteBuffer mappedByteBuffer;
    private FileChannel fileChannel;
    private CharBuffer charBuffer;

    public MappedByteBufferStorage(Path pathToRead, int size) throws IOException, URISyntaxException {
        try {
            fileChannel = (FileChannel) Files.newByteChannel(
                    pathToRead, EnumSet.of(StandardOpenOption.READ, StandardOpenOption.WRITE));
            mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, size);
            if (mappedByteBuffer != null) {
                charBuffer = StandardCharsets.UTF_8.decode(mappedByteBuffer);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void putMappedByteBuffer(int index, byte value) {
        mappedByteBuffer.put(index, value);
    }

    public byte getMappedByteBuffer(int index) {
        return mappedByteBuffer.get(index);
    }

    public String getFromMappedByteBuffer() {
        return charBuffer.toString().trim();
    }

    public void close() throws IOException {
        if (fileChannel != null) {
            fileChannel.close();
        }
    }
}

