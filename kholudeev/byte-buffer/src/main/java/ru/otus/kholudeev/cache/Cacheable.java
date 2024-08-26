package ru.otus.kholudeev.cache;

public interface Cacheable<K, V> {
    void add(K key, V value);

    V get(K key);

    void clear();

    int getSize();

    void showCacheInfo();
}
