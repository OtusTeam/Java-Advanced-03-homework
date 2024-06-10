package ru.otus.services.filereader;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class FileReaderImpl implements FileReader {
    private final String charset;
    private Path path;

    public FileReaderImpl(String path) {
        Path pth = Paths.get(path);
        checkPath(pth);
        this.charset = "UTF-8";
    }

    public FileReaderImpl() {
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
        try {
            List<String> lines = Files.readAllLines(
                    path.resolve(name),
                    Charset.forName(charset));
            lines.forEach(l -> content.append(l).append("\n"));
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
