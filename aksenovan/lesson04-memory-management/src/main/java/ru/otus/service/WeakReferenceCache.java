package ru.otus.service;

import java.lang.ref.WeakReference;

public class WeakReferenceCache extends AbstractCache<String, String> {
    @Override
    public void put(String key, String value) {
        cache.put(key, new WeakReference<>(value));
    }
}
