package ru.otus.service;

public interface FileService {
    String getFile(String fileName);

    void setFolder(String folderName);

    boolean isPathFilled();
}
