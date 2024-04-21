package ru.skilanov.service;

public interface CacheService<K, V> {

    V get(K key);

    void load(String path, K key);
}
