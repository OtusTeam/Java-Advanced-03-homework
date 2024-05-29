package ru.otus.java.advanced.fileservice;

import ru.otus.java.advanced.cache.AbstractReferenceCache;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class AbstractFileService {

    protected AbstractReferenceCache<String, String> cache;
    protected String dir;

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String readFile(String fileName) {
        if (!cache.contains(fileName)) {
            System.out.println("Файл не найден в кэше");
            cache.put(fileName, getFileContent(fileName));
            System.out.println("Файл загружен в кэш");
        }
        System.out.println("Достаю файл из кэша");
        return cache.get(fileName);
    }

    public boolean uploadFile(String fileName) {
        try {
            cache.put(fileName, getFileContent(fileName));
            System.out.println("Файл загружен в кэш");
            return true;
        } catch (Exception e) {
            System.out.println("Ошибка загружен файла в кэш");
            return false;
        }
    }

    private String getFileContent(String fileName) {
        try {
            System.out.println("Начинаю чтение файла");
            return Files.readString(Paths.get(dir + "/" + fileName), Charset.defaultCharset());
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
            throw new RuntimeException(e);
        }
    }

}
