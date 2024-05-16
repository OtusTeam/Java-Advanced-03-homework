package ru.nicshal.advanced;

import ru.nicshal.advanced.managers.FileManager;

import java.io.IOException;

public class MemoryManagementApplication {

    public static void main(String[] args) throws IOException {
        FileManager manager = new FileManager();
        manager.run();
    }

}