package ru.skilanov.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileRepositoryImpl<K, V> implements FileRepository<K, V> {
    Logger logger = LoggerFactory.getLogger(FileRepositoryImpl.class);

    @Override
    public V readFile(K file, String path) {
        var dir = path + "/" + file;
        try (var reader = new FileReader(dir);
             var bufferedReader = new BufferedReader(reader)) {
            return (V) bufferedReader.lines()
                    .collect(Collectors.joining("\n"));
        } catch (IOException e) {
            logger.atError().log("File doesn't exist");
            throw new RuntimeException("File doesn't exist");
        }
    }

    @Override
    public List<K> getAllFiles(K path) {
        var directory = new File(path.toString());
        if (!directory.exists() || !directory.isDirectory()) {
            logger.atError().log("Directory doesn't exist");
            return new ArrayList<>();
        }

        var files = directory.listFiles();
        return (List<K>) Arrays.stream(files).toList();
    }
}
