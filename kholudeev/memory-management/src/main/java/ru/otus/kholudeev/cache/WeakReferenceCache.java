package ru.otus.kholudeev.cache;

import java.lang.ref.WeakReference;

public class WeakReferenceCache extends BaseCache {
    @Override
    public void put(String key, String value) {
        WeakReference<String> weakReference = new WeakReference<>(value, referenceQueue);
        cache.put(key, weakReference);
    }
}
