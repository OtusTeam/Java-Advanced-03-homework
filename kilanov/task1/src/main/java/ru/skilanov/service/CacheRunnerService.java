package ru.skilanov.service;

import java.util.List;

public interface CacheRunnerService {

    void setDirectory(String path);
    List<String> getAllFiles();

    void cacheFile(String fileName);

    String getFileByName(String fileName);
}
