package ru.otus.kholudeev.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

import static java.nio.channels.FileChannel.MapMode.READ_ONLY;

public class MappedByteBufferFileReader extends BaseFileReader{
    @Override
    public String read(String fileName) throws IOException{
        try(RandomAccessFile file = new RandomAccessFile(path.resolve(fileName).toString(), "r"))
        {
            FileChannel channel = file.getChannel();
            int fileSize = (int) channel.size();
            MappedByteBuffer mappedByteBuffer = channel.map(READ_ONLY, 0, fileSize);

            byte[] bytes = new byte[fileSize];
            mappedByteBuffer.get(bytes);

            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IOException("Error read file " + fileName, e);
        }
    }
}
