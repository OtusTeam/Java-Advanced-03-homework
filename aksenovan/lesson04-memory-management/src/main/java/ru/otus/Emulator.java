package ru.otus;

import ru.otus.service.FileSystemCache;

import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class Emulator {

    private final FileSystemCache cache;

    public Emulator(File directory) {
        this.cache = new FileSystemCache(directory);
    }

    public void loadFile(String filename) {
        cache.loadData(filename);
        System.out.println("Loaded file: " + filename);
    }

    public void printFile(String filename) {
        String data = cache.getData(filename);
        System.out.println(Objects.requireNonNullElseGet(data, () -> "File not found in cache: " + filename));
    }

    public void runCommand(String command) {
        String filename;

        switch (command) {
            case "load":
                filename = promptUserForFilename("Enter a filename to load:");
                if (filename != null) {
                    loadFile(filename);
                }
                break;
            case "print":
                filename = promptUserForFilename("Enter a filename to print:");
                if (filename != null) {
                    printFile(filename);
                }
                break;
            case "dir":
                filename = promptUserForFilename("Enter a new directory name:");
                cache.setDirectory(new File(filename));
                break;
            default:
                System.out.println("Unknown command.");
                break;
        }
    }

    private String promptUserForFilename(String messasge) {
        System.out.println(messasge);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
