package ru.otus.kholudeev.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class ByteBufferFileReader extends BaseFileReader{
    @Override
    public String read(String fileName) throws IOException {
        try (SeekableByteChannel channel = Files.newByteChannel(path.resolve(fileName), StandardOpenOption.READ)){
            int fileSize = (int) channel.size();

            ByteBuffer byteBuffer = ByteBuffer.allocate(fileSize);
            channel.read(byteBuffer);
            byteBuffer.flip();

            byte[] bytes = new byte[fileSize];
            byteBuffer.get(bytes);
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IOException("Error read file " + fileName, e);
        }
    }
}
