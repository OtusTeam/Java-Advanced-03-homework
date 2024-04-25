package ru.otus.evgrez.component;

import ru.otus.evgrez.cache.Cache;

import java.util.Optional;
import java.util.function.Function;

public class CacheStrategy<K, V> {

    private final Cache<K, V> cache;
    private final Function<K, V> cacheLoader;

    public CacheStrategy(Cache<K, V> cache, Function<K, V> cacheLoader) {
        this.cache = cache;
        this.cacheLoader = cacheLoader;
    }

    public Optional<V> getByKey(K key) {
        if (cache.containsKey(key)) {
            logging("Value by %s found in cache%n", key);
            return cache.getByKey(key);
        } else {
            logging("Value by %s not found in cache%n", key);
            V value = cacheLoader.apply(key);
            return cache.put(key, value);
        }
    }

    private static void logging(String s, Object... args) {
        System.out.printf(s, args);
    }

}
