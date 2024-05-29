package ru.otus.java.advanced.fileservice;

import ru.otus.java.advanced.cache.SoftReferenceCache;

public class SoftReferenceFileService extends AbstractFileService {

    public SoftReferenceFileService() {
        super.cache = new SoftReferenceCache<>();
    }
}
