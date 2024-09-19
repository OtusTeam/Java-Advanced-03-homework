package ru.otus.kholudeev.util;

import ru.otus.kholudeev.io.BaseFileReader;
import ru.otus.kholudeev.io.MappedByteBufferFileReader;
import ru.otus.kholudeev.io.ByteBufferFileReader;
import ru.otus.kholudeev.cache.BaseCache;
import ru.otus.kholudeev.cache.SoftReferenceCache;
import ru.otus.kholudeev.cache.WeakReferenceCache;
import ru.otus.kholudeev.exception.CacheKeyDoesntExistsException;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Emulator {
    public static final String WRONG_ACTION = "Please try again";
    public static final String SWITCH_ACTION = """
            Please select option:
                1. Add file to cache
                2. Get file from cache
                3. Show cache info
                4. Clear cache
                5. Exit
            """;
    private BaseFileReader fileReader;
    private final List<String> action = Arrays.asList("1", "2", "3", "4", "5");
    private Scanner scanner;
    private BaseCache cache;

    private static BaseCache createCache(boolean isWeak) {
        return isWeak ? new WeakReferenceCache() : new SoftReferenceCache();
    }

    private static BaseFileReader createFileReader(boolean isByteBuffer) {
        return isByteBuffer ? new ByteBufferFileReader() : new MappedByteBufferFileReader();
    }

    public void dialog() {
        scanner = new Scanner(System.in);
        cache = choseCacheType();
        fileReader = choseFileReaderType();
        try {
            setFileDirectory();
            mainMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private BaseFileReader choseFileReaderType(){
        return createFileReader(returnInputType(Boolean.FALSE));
    }

    private BaseCache choseCacheType() {
        return createCache(returnInputType(Boolean.TRUE));
    }

    private boolean returnInputType(Boolean isCache) {
        int input;
        String[] out = Boolean.TRUE.equals(isCache) ? new String[]{"cache", "WeakReference", "SoftReference"} : new String[]{"file reader", "ByteBuffer", "MappedByteBuffer"};
        System.out.printf("Please choose %1s type\nEnter 1 if you want to choose %2s.\nAnother value if you want to choose %3s%n", out[0], out[1], out[2]);
        try {
            input = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            input = 1;
        }
        if (input == 1) {
            System.out.printf("You choose %1s %2s...\n", out[1], out[0]);
            return Boolean.TRUE;
        }
        System.out.printf("You choose %1s %2s...\n", out[2], out[0]);
        return Boolean.FALSE;
    }

    private void setFileDirectory() {
        System.out.println("Please set file path: ");
        String path = scanner.nextLine();
        fileReader.setPath(path);
    }

    private void mainMenu() throws IOException {
        System.out.println(SWITCH_ACTION);
        String option = scanner.nextLine();
        if (!action.contains(option)) {
            System.out.println(WRONG_ACTION);
            mainMenu();
        } else if (Objects.equals(option, "5")) {
            System.out.println("Exit");
        } else {
            // Основной диалог с юзером
            switch (option) {
                case "1" -> addFile();
                case "2" -> getFile();
                case "3" -> cache.showCacheInfo();
                case "4" -> cache.clear();
                default -> {
                    System.out.println(WRONG_ACTION);
                    mainMenu();
                }
            }
            mainMenu();
        }
    }

    private void addFile() throws IOException {
        System.out.println("Write file name:");
        String fileName = scanner.nextLine();
        try {
            cache.put(fileName, fileReader.read(fileName));
            System.out.printf("Add file %s to cache%n", fileName);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            mainMenu();
        }
    }

    private void getFile() throws IOException {
        System.out.println("Write file name:");
        try{
            ByteBuffer byteBuffer = cache.getValue(scanner.nextLine());
            byteBuffer.position(0);
            byte[] bytes = new byte[byteBuffer.remaining()];
            byteBuffer.get(bytes);
            String fileContent = new String(bytes, StandardCharsets.UTF_8);
            System.out.println("File content is :\n" + fileContent);
        }
        catch (CacheKeyDoesntExistsException e) {
            System.out.println(e.getMessage());
            mainMenu();
        }
    }
}
