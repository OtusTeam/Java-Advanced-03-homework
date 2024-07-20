package ru.otus.kholudeev.util;

import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@NoArgsConstructor
public class FileReader {
    private Path path;

    public void setPath(String path) {
        Path pth = Paths.get(path);
        if (Files.exists(pth)) {
            this.path = pth;
        } else throw new IllegalArgumentException("Path doesn't exist");
    }

    public String read(String name) throws IOException {
        var content = new StringBuilder();
        try {
            String charset = "UTF-8";
            List<String> lines = Files.readAllLines(
                    path.resolve(name),
                    Charset.forName(charset));
            lines.forEach(l -> content.append(l).append("\n"));
        } catch (IOException e) {
            throw new IOException("Error read file:" + e.getMessage());
        }
        return content.toString();
    }
}
