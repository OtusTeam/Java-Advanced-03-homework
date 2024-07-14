package ru.otus.services.filereader;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@AllArgsConstructor
@Getter
@Setter
public class FileReaderMBB implements FileReader {
    private final String charset;
    private Path path;

    public FileReaderMBB(String path) {
        Path pth = Paths.get(path);
        checkPath(pth);
        this.charset = "UTF-8";
    }

    public FileReaderMBB() {
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
        Path file = path.resolve(name);
        String content = null;

        try (RandomAccessFile accessFile = new RandomAccessFile(file.toFile(), "r");
             FileChannel fileChannel = accessFile.getChannel()) {

            MappedByteBuffer mappedByteBuffer = fileChannel.map(
                    FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());

            if (mappedByteBuffer != null) {
                CharBuffer charBuffer = Charset.forName(charset).decode(mappedByteBuffer);
                content = charBuffer.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    private void checkPath(Path pth) {
        if (!Files.exists(pth)) {
            throw new RuntimeException("Path doesn't exist");
        } else {
            this.path = pth;
        }
    }
}
