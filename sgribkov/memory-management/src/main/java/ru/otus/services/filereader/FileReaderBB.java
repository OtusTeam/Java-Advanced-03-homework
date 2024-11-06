package ru.otus.services.filereader;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@AllArgsConstructor
@Getter
@Setter
public class FileReaderBB implements FileReader {
    private Path path;

    public FileReaderBB(String path) {
        Path pth = Paths.get(path);
        checkPath(pth);
    }

    public FileReaderBB() {

    }

    @Override
    public void setPath(String path) {
        Path pth = Paths.get(path);
        checkPath(pth);
    }

    @Override
    public boolean hasPath() {
        return path != null;
    }

    @Override
    public ByteBuffer read(String name) {
        Path file = path.resolve(name);
        ByteBuffer buffer = null;

        try (RandomAccessFile accessFile = new RandomAccessFile(file.toFile(), "r");
             FileChannel fileChannel = accessFile.getChannel()) {

            buffer = ByteBuffer.allocateDirect((int) fileChannel.size());
            fileChannel.read(buffer);
            buffer.flip();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buffer;
    }

    private void checkPath(Path pth) {
        if (!Files.exists(pth)) {
            throw new RuntimeException("Path doesn't exist");
        } else {
            this.path = pth;
        }
    }
}
