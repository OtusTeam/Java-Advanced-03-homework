package ru.skilanov.repository;

import java.util.List;

public interface FileRepository<K, V> {

    V readFile(K file, String path);

    List<K> getAllFiles(K path);
}
