package ru.nicshal.advanced.nio.handlers;

public interface FileContentHandler {

    void copyFileContents(String filePath);

    default void reverseFileContents(String filePath) {}

}
