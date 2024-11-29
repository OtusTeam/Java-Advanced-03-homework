package ru.otus;

import ru.otus.service.AbstractCache;
import ru.otus.service.FileService;
import ru.otus.service.FileServiceImpl;
import ru.otus.service.SoftReferenceCache;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        final AbstractCache<String, String> cache = new SoftReferenceCache();
        final FileService fileService = new FileServiceImpl(cache);
        final Emulator emulator = new Emulator(fileService);
        final Scanner scanner = new Scanner(System.in);

        boolean shouldExit = false;
        while (!shouldExit) {
            System.out.println("input \"setdir\" or \"getfile\" or \"exit\" command");
            String command = scanner.nextLine();

            if ("exit".equals(command)) {
                shouldExit = true;
            } else {
                emulator.runCommand(command);
            }
        }
    }
}
