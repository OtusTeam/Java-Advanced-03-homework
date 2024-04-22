package ru.skilanov.repository;

import org.springframework.stereotype.Component;
import ru.skilanov.exception.FileDoesntExistException;
import ru.skilanov.exception.NoSuchDirectoryException;

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
            throw new FileDoesntExistException("File doesnt exist");
        }
        return (V) builder.toString();
    }

    @Override
    public List<K> getAllFiles(K path) {
        var directory = new File(path.toString());
        var files = directory.listFiles();
        if (files == null) {
            throw new NoSuchDirectoryException("Directory doesnt exist");
        }
        return (List<K>) Arrays.stream(files).toList();
    }
}
