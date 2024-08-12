package ru.otus.java.advanced.cache;

import java.lang.ref.WeakReference;

public class WeakReferenceCache<K, V> extends AbstractReferenceCache<K, V> {

    @Override
    public void put(K key, V value) {
        cache.put(key, new WeakReference<>(value));
    }
}
