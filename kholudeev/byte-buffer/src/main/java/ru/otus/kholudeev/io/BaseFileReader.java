package ru.otus.kholudeev.io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class BaseFileReader implements FileReaderInstance {
    protected Path path;

    public void setPath(String path) {
        Path pth = Paths.get(path);
        if (Files.exists(pth)) {
            this.path = pth;
        } else throw new IllegalArgumentException("Path doesn't exist");
    }
}
