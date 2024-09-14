package ru.otus.kholudeev.cache;

import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;

public class WeakReferenceCache extends BaseCache {
    @Override
    public void put(String key, ByteBuffer value) {
        WeakReference<ByteBuffer> weakReference = new WeakReference<>(value, referenceQueue);
        cache.put(key, weakReference);
    }
}
