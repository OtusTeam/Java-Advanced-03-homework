package ru.otus.kholudeev.util;

import ru.otus.kholudeev.cache.BaseCache;
import ru.otus.kholudeev.cache.SoftReferenceCache;
import ru.otus.kholudeev.cache.WeakReferenceCache;
import ru.otus.kholudeev.exception.CacheKeyDoesntExistsException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

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
    private static final FileReader fileReader = new FileReader();
    private final List<String> action = Arrays.asList("1", "2", "3", "4", "5");
    private Scanner scanner;
    private BaseCache cache;

    private static BaseCache createCache(boolean isWeak) {
        return isWeak ? new WeakReferenceCache() : new SoftReferenceCache();
    }

    public void dialog() {
        scanner = new Scanner(System.in);
        cache = choseCacheType();
        try {
            setFileDirectory();
            mainMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private BaseCache choseCacheType() {
        int input;
        System.out.println("Please choose cache type");
        System.out.println("Enter 1 if you want to choose WeakReference.\n" +
                "Another value if you want to choose SoftReference");
        try {
            input = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            input = 1;
        }
        if (input == 1) {
            System.out.println("You choose WeakReference cache...");
            return createCache(Boolean.TRUE);
        }
        System.out.println("You choose SoftReference cache...");
        return createCache(Boolean.FALSE);
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
            String fileContent = cache.getValue(scanner.nextLine());
            System.out.println("File content is :\n" + fileContent);
        }
        catch (CacheKeyDoesntExistsException e) {
            System.out.println(e.getMessage());
            mainMenu();
        }
    }
}
