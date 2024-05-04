package com.memory.menu;

import java.util.Scanner;
import java.util.logging.Logger;

import com.memory.cache.FileCacheImpl;

public class Emulator {

    Logger logger = Logger.getLogger(Emulator.class.getName());

    private FileCacheImpl fileCache;

    public void showMenu() {
        System.out.println("""
                1 - Set caching directory
                2 - Enter file name to load in cache
                3 - Get file content from cache
                4 - Get value from file(load into cache if not loaded before)
                5 - Exit
                                
                6 - Call garbage collector
                """);

        Scanner input = new Scanner(System.in);
        String commandNumber = input.nextLine();

        switch (commandNumber) {
            case "1":
                fileCache = setCachingDirectory();
                break;
            case "2":
                loadFileIntoCache();
                break;
            case "3":
                getFileContentFromCache();
                break;
            case "4":
                getValueFromFile();
                break;
            case "5":
                System.exit(0);
                break;
            case "6" :
                System.gc();
                break;
            default:
                System.out.println("Command doesn't exist");
        }
    }

    private void getValueFromFile() {
        if (fileCache == null) {
            System.out.println("Set caching directory first");
            return;
        }

        Scanner input = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String filename = input.nextLine();

        String content = fileCache.getValue(new FileCacheImpl.FileCacheKey(filename));
        System.out.println(content);
    }

    private void getFileContentFromCache() {
        if (fileCache == null) {
            System.out.println("Set caching directory first");
            return;
        }

        Scanner input = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String filename = input.nextLine();

        String fileContent = fileCache.getValueFromCache(new FileCacheImpl.FileCacheKey(filename));
        System.out.println(fileContent);
    }

    private void loadFileIntoCache() {
        if (fileCache == null) {
            System.out.println("Set caching directory first");
            return;
        }

        Scanner input = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String filename = input.nextLine();

        fileCache.loadValueIntoCache(new FileCacheImpl.FileCacheKey(filename));
        System.out.println("File " + filename + " loaded into cache");
    }

    private FileCacheImpl setCachingDirectory() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter directory name: ");
        String directory = input.nextLine();

        return new FileCacheImpl(directory);
    }
}
