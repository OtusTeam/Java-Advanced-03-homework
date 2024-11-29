package ru.otus;

import ru.otus.service.FileService;

import java.util.Scanner;

public class Emulator {

    private final FileService fileService;

    public Emulator(FileService fileService) {
        this.fileService = fileService;
    }

    public void runCommand(String command) {
        switch (command) {
            case "setdir":
                String dir = promptUser("Enter a new directory name:");
                fileService.setFolder(dir);
                break;
            case "getfile":
                if (fileService.isPathFilled()) {
                    String fileName = promptUser("Enter file name to get from dir");
                    System.out.println(fileService.getFile(fileName));
                }
        }
    }

    private String promptUser(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
