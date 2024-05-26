package ru.otus.services.filereader;

public interface FileReader {
    void setPath(String path);
    boolean hasPath();
    String read(String name);
}
