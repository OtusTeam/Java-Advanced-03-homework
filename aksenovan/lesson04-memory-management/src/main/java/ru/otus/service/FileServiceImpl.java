package ru.otus.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileServiceImpl implements FileService {
    private final AbstractCache<String, String> cache;
    private String path;

    public FileServiceImpl(AbstractCache<String, String> cache) {
        this.cache = cache;
    }

    @Override
    public String getFile(String fileName) {
        if (!isPathFilled())
            return null;

        final String fullPath = getFullPath(fileName);
        final String fileFromCache = cache.get(fullPath);

        if (fileFromCache == null) {
            System.out.println("writing to cache");
            String fileContent = getFileContent(fullPath);
            cache.put(fullPath, fileContent);
            System.out.println("getting data from cache");
            return cache.get(fullPath);
        }

        System.out.println("getting data from cache");
        return fileFromCache;
    }

    @Override
    public void setFolder(String path) {
        this.path = path;
    }

    private String getFullPath(String fileName) {
        return path + fileName;
    }

    private String getFileContent(String fullPath) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(fullPath));
            return new String(bytes, Charset.defaultCharset());
        } catch (IOException e) {
            throw new IllegalStateException("Couldn't read file from " + fullPath);
        }
    }

    @Override
    public boolean isPathFilled() {
        final boolean isPathFilled = path != null && !path.isEmpty() && !path.isBlank();
        if (!isPathFilled)
            System.out.println("FolderName is not set. Set folder first.");
        return isPathFilled;
    }
}
