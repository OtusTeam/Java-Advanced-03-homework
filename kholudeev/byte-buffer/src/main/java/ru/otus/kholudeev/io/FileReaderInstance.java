package ru.otus.kholudeev.io;

import java.io.IOException;

public interface FileReaderInstance {
    void setPath(String path);

    String read(String fileName) throws IOException;
}
