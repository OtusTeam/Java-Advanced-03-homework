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
    private final String charset;
    private Path path;

    public FileReaderBB(String path) {
        Path pth = Paths.get(path);
        checkPath(pth);
        this.charset = "UTF-8";
    }

    public FileReaderBB() {
        this.path = null;
        this.charset = "UTF-8";
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
    public String read(String name) {
        var content = new StringBuilder();
        Path file = path.resolve(name);

        try (RandomAccessFile accessFile = new RandomAccessFile(file.toFile(), "r");
             FileChannel fileChannel = accessFile.getChannel()) {

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            while (fileChannel.read(byteBuffer) > 0) {
                byteBuffer.flip();
                byte[] bytes = new byte[byteBuffer.remaining()];
                byteBuffer.get(bytes);
                String packet = new String(bytes, charset);
                content.append(packet);
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }

    private void checkPath(Path pth) {
        if (!Files.exists(pth)) {
            throw new RuntimeException("Path doesn't exist");
        } else {
            this.path = pth;
        }
    }
}
