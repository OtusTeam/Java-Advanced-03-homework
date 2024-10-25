package ru.otus.services.filereader;

import java.nio.ByteBuffer;

public interface FileReader {
    void setPath(String path);
    boolean hasPath();
    ByteBuffer read(String name);
}
