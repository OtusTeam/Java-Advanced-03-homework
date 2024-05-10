package ru.skilanov.cache;

public interface Cache<K, V> {

    void load(K key, V value);
}
