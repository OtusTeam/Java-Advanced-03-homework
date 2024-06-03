package ru.otus.java.advanced.cache;

import java.lang.ref.SoftReference;

public class SoftReferenceCache<K, V> extends AbstractReferenceCache<K, V> {

    @Override
    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }
}
