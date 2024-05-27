package ru.otus.service;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    private final Map<K, V> cache = new HashMap<>();

    public abstract void loadData(K key);

    public abstract V getData(K key);

    protected void put(K key, V value) {
        cache.put(key, value);
    }

    protected V get(K key) {
        return cache.get(key);
    }
}
