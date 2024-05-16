package ru.nicshal.advanced.caches;

import java.lang.ref.Reference;
import java.util.HashMap;
import java.util.Map;

public abstract class ReferenceCache {

    Map<String, Reference<String>> cacheMap;

    public ReferenceCache() {
        this.cacheMap = new HashMap<>();
    }

    public boolean containsKey(String filePath) {
        return cacheMap.containsKey(filePath) && cacheMap.get(filePath).get() != null;
    }

    abstract public void put(String filePath, String content);

    public String get(String filePath) {
        return cacheMap.get(filePath).get();
    }

}