package ru.otus.service;

import java.lang.ref.SoftReference;

public class SoftReferenceCache extends AbstractCache<String, String> {
    @Override
    public void put(String key, String value) {
        cache.put(key, new SoftReference<>(value));
    }
}
