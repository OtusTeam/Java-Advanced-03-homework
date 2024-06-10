package ru.otus.java.advanced.fileservice;

import ru.otus.java.advanced.cache.WeakReferenceCache;

public class WeakReferenceFileService extends AbstractFileService {

    public WeakReferenceFileService() {
        super.cache = new WeakReferenceCache<>();
    }
}
