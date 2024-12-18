package ru.otus.java.advanced.cache.menu;

import ru.otus.java.advanced.filereader.AbstractFileReader;
import ru.otus.java.advanced.filereader.ByteBufferFileReader;
import ru.otus.java.advanced.filereader.MappedByteBufferFileReader;
import ru.otus.java.advanced.fileservice.AbstractFileService;
import ru.otus.java.advanced.fileservice.SoftReferenceFileService;
import ru.otus.java.advanced.fileservice.WeakReferenceFileService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Emulator {

    private AbstractFileReader fileReader;
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void start() {
        fileReader = getFileReader();
        AbstractFileService fileService = getFileService();
        printDialogueMessage();
        while (true) {
            String command = getAnswer();
            switch (command) {
                case "1" -> fileService.setDir(getAnswer());
                case "2" -> fileService.uploadFile(getAnswer());
                case "3" -> System.out.println(fileService.readFile(getAnswer()));
                case "4" -> { return; }
                default -> System.out.println("Неверное значение");
            }
        }
    }

    private AbstractFileService getFileService() {
        System.out.println("Выберите тип кэширования: {0} - WeakReference; {1} - SoftReference");
        try {
            String fileReaderType = reader.readLine();
            switch (fileReaderType) {
                case "0" -> { return new WeakReferenceFileService(fileReader); }
                case "1" -> { return new SoftReferenceFileService(fileReader); }
                default -> {
                    System.out.println("Введено неверное значение: " + fileReaderType);
                    return getFileService();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private AbstractFileReader getFileReader() {
        System.out.println("Выберите тип буфера: {0} - ByteBuffer; {1} - MappedByteBuffer");
        try {
            String bufferType = reader.readLine();
            switch (bufferType) {
                case "0" -> { return new ByteBufferFileReader(); }
                case "1" -> { return new MappedByteBufferFileReader(); }
                default -> {
                    System.out.println("Введено неверное значение: " + bufferType);
                    return getFileReader();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getAnswer() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Что-то пошло не так, повторите попытку");
            return getAnswer();
        }
    }

    private void printDialogueMessage() {
        System.out.println("""
                Введите:
                1 - для выбора директории
                2 - для загрузки содержимого файла в кэш
                3 - для получения данных из кэша
                4 - для выхода
                """);
    }

}
