package ru.skilanov.cache;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.WeakHashMap;

@Component
public class SimpleCacheImpl<K, V> implements Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();

    @Override
    public void load(K key, V value) {
        cache.put(key, value);
    }
}
