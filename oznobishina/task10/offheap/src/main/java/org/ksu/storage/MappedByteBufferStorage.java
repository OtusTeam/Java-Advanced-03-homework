package org.ksu.storage;

import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;


public class MappedByteBufferStorage implements Storage {
    private MappedByteBuffer mappedByteBuffer;
    private CharBuffer charBuffer;

    public MappedByteBufferStorage(Path pathToRead, int size) {
        try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(
                pathToRead, EnumSet.of(StandardOpenOption.READ, StandardOpenOption.WRITE))) {
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

    @Override

    public String getFromByteBuffer() {
        return charBuffer.toString().trim();
    }
}

