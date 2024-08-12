package ru.otus.java.advanced.cache;

import java.lang.ref.Reference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractReferenceCache <K, V> {

    protected final Map<K, Reference<V>> cache = new ConcurrentHashMap<>();

    public V get(K key) {
        return cache.get(key).get();
    }

    public abstract void put(K key, V value);

    public boolean contains(K key) {
        return cache.containsKey(key);
    }

}
