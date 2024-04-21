package ru.skilanov.repository;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

@Component
public class FileRepositoryImpl<K, V> implements FileRepository<K, V> {
    @Override
    public V readFile(K file, String path) {
        StringBuilder builder = new StringBuilder();
        var dir = path + "/" + file;
        try (var reader = new FileReader(dir);
             var bufferedReader = new BufferedReader(reader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line).append("\n");
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return (V) builder.toString();
    }

    @Override
    public List<K> getAllFiles(K path) {
        var directory = new File(path.toString());
        return (List<K>) Arrays.stream(directory.listFiles()).toList();
    }
}
