package ru.otus.kholudeev.io;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface FileReaderInstance {
    void setPath(String path);

    ByteBuffer read(String fileName) throws IOException;
}
