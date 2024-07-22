package ru.otus.kholudeev.cache;

import java.lang.ref.SoftReference;

public class SoftReferenceCache extends BaseCache {
    @Override
    public void put(String key, String value) {
        SoftReference<String> softReference = new SoftReference<>(value, referenceQueue);
        cache.put(key, softReference);
    }
}
