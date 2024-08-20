package ru.otus.java.advanced.fileservice;

import ru.otus.java.advanced.cache.AbstractReferenceCache;
import ru.otus.java.advanced.filereader.AbstractFileReader;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public abstract class AbstractFileService {

    public AbstractFileService(AbstractFileReader fileReader) {
        this.fileReader = fileReader;
    }

    protected AbstractReferenceCache<String, ByteBuffer> cache;
    protected AbstractFileReader fileReader;
    protected String dir;

    public void setDir(String dir) {
        this.dir = dir;
        this.fileReader.setDir(dir);
    }

    public String readFile(String fileName) {
        if (!cache.contains(fileName)) {
            System.out.println("Файл не найден в кэше");
            cache.put(fileName, getFileContent(fileName));
            System.out.println("Файл загружен в кэш");
        }
        System.out.println("Достаю файл из кэша");
        return String.valueOf(StandardCharsets.UTF_8.decode(cache.get(fileName)));
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

    private ByteBuffer getFileContent(String fileName) {
        System.out.println("Начинаю чтение файла");
        return fileReader.readFile(fileName);
    }

}
