package ru.nicshal.advanced.caches;

import java.lang.ref.SoftReference;

public class SoftReferenceCacheImpl extends ReferenceCache {

    @Override
    public void put(String filePath, String content) {
        cacheMap.put(filePath, new SoftReference<>(content));
    }

}