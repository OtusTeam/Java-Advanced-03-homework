package ru.nicshal.advanced.nio.managers;

import ru.nicshal.advanced.nio.handlers.ByteBufferHandler;
import ru.nicshal.advanced.nio.handlers.FileContentHandler;
import ru.nicshal.advanced.nio.handlers.MappedByteBufferHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static ru.nicshal.advanced.nio.utils.ApplicationConstant.*;

public class FileManager {

    FileContentHandler handler;

    public FileManager() {
        this.handler = BUFFER_TYPE.equals(BYTE_BUFFER) ? new ByteBufferHandler(BUFFER_LENGTH) : new MappedByteBufferHandler();
    }

    public void run() {
        List<String> fileList = generateFileList();
        if (fileList.isEmpty()) {
            System.out.println("В корневом каталоге не найдено текстовых файлов");
        } else {
            printFileList(fileList);
            Scanner scanner = new Scanner(System.in);
            String inputFileName = "";
            while (!EXIT.equals(inputFileName)) {
                inputFileName = setFileName(scanner);
                if (fileList.contains(inputFileName)) {
                    long startTime = System.nanoTime();
                    handler.copyFileContents(WORK_DIRECTORY_PATH + inputFileName);
                    handler.reverseFileContents(WORK_DIRECTORY_PATH + inputFileName + COPY_POSTFIX);
                    long endTime = System.nanoTime();
                    System.out.println("Время обработки: " + (endTime - startTime) / 1000000.0 + " ms");
                } else {
                    if (EXIT.equals(inputFileName)) break;
                    System.out.println("Файл " + inputFileName + " не найден в рабочем каталоге.");
                }
            }
        }
    }

    private List<String> generateFileList() {
        List<String> fileList = new ArrayList<>();
        File file = new File(WORK_DIRECTORY_PATH);
        for (File item : Objects.requireNonNull(file.listFiles())) {
            if (item.getName().endsWith(".txt")) {
                fileList.add(item.getName());
            }
        }
        return fileList;
    }

    private void printFileList(List<String> fileList) {
        System.out.println("Для просмотра и обработки доступны следующие текстовые файлы:");
        for (String fileName : fileList) {
            System.out.println(fileName);
        }
    }

    private String setFileName(Scanner scanner) {
        System.out.println("Введите имя файла, с которым Вы будете работать:");
        return scanner.nextLine();
    }

}
