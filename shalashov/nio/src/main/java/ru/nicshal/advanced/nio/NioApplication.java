package ru.nicshal.advanced.nio;

import ru.nicshal.advanced.nio.managers.FileManager;

public class NioApplication {
    public static void main(String[] args) {
        FileManager manager = new FileManager();
        manager.run();
    }
}
