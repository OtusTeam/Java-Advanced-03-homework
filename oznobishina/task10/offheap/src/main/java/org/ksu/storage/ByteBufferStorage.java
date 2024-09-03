package org.ksu.storage;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class ByteBufferStorage {

    private ByteBuffer byteBuffer;
    private FileChannel fileChannel;
    private CharBuffer charBuffer;

    public ByteBufferStorage(Path pathToRead, int size) {
        try {
            byteBuffer = ByteBuffer.allocateDirect(size);  // Выделение памяти вне кучи
            fileChannel = (FileChannel) Files.newByteChannel(
                    pathToRead, EnumSet.of(StandardOpenOption.READ, StandardOpenOption.WRITE));
            fileChannel.read(byteBuffer); // Read the file data into the ByteBuffer
            byteBuffer.flip();
            charBuffer = StandardCharsets.UTF_8.decode(byteBuffer);
            System.out.println("File successfully read into ByteBuffer.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void putByteBuffer(int index, byte value) {
        byteBuffer.put(index, value);
    }

    public byte getByteBuffer(int index) {
        return byteBuffer.get(index);
    }

    public String getFromByteBuffer() {
        return charBuffer.toString().trim();
    }

    public void close() throws IOException {
        if (fileChannel != null) {
            fileChannel.close();
        }
    }
}

