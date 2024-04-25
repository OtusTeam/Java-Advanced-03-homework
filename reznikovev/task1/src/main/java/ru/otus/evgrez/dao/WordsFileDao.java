package ru.otus.evgrez.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WordsFileDao {

    private final Path path;

    public WordsFileDao(String path) {
        this.path = createFolderPath(path);
    }

    public List<String> getWords(String fileName) {
        isEmpty(fileName);
        Path filePath = path.resolve(fileName);
        fileExists(filePath);
        System.out.printf("Read from %s%n", filePath.toUri());
        try {
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            throw new UnsupportedOperationException("Error reading file", e.getCause());
        }
    }

    private static Path createFolderPath(String path) {
        isEmpty(path);
        var tPath = Path.of(path);
        directoryExists(tPath);
        return tPath;
    }

    private static void isEmpty(String path) {
        if (path == null || path.isEmpty() || path.isBlank()) {
            throw new UnsupportedOperationException("The path cannot be empty");
        }
    }

    private static void fileExists(Path path) {
        if (!Files.isRegularFile(path)) {
            throw new UnsupportedOperationException("File not found");
        }
    }

    private static void directoryExists(Path path) {
        if (!Files.isDirectory(path)) {
            throw new UnsupportedOperationException("Folder not found: " + path.toUri());
        }
    }
}
