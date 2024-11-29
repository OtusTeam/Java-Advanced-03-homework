package ru.otus.service;

import java.lang.ref.Reference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractCache<K, V> {

    protected final Map<K, Reference<V>> cache = new ConcurrentHashMap<>();

    public abstract void put(K key, V value);

    public V get(K key) {
        if (cache.get(key) == null)
            return null;
        return cache.get(key).get();
    }

    public boolean contains(K key) {
        return cache.containsKey(key);
    }
}
