package ru.otus;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File directory = new File("aksenovan/lesson04-memory-management/src/main/resources/cache/");
        Emulator emulator = new Emulator(directory);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("emulator> ");
            String command = scanner.nextLine();
            emulator.runCommand(command);
        }
    }
}
