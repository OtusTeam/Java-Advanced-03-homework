package ru.nicshal.advanced.caches;

import java.lang.ref.WeakReference;

public class WeakReferenceCacheImpl extends ReferenceCache {

    @Override
    public void put(String filePath, String content) {
        cacheMap.put(filePath, new WeakReference<>(content));
    }

}