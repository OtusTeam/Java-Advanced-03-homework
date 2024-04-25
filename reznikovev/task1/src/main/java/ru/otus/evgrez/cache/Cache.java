package ru.otus.evgrez.cache;

import java.util.Optional;

public interface Cache<K, V> {

    boolean containsKey(K key);

    Optional<V> getByKey(K key);

    Optional<V> put(K key, V value);

    int size();
}
