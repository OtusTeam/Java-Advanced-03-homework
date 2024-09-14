package ru.otus.kholudeev.cache;

import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;

public class SoftReferenceCache extends BaseCache {
    @Override
    public void put(String key, ByteBuffer value) {
        SoftReference<ByteBuffer> softReference = new SoftReference<>(value, referenceQueue);
        cache.put(key, softReference);
    }
}
