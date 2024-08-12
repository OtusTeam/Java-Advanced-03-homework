package ru.otus.java.advanced.fileservice;

import ru.otus.java.advanced.cache.WeakReferenceCache;
import ru.otus.java.advanced.filereader.AbstractFileReader;

public class WeakReferenceFileService extends AbstractFileService {

    public WeakReferenceFileService(AbstractFileReader fileReader) {
        super(fileReader);
        super.cache = new WeakReferenceCache<>();
    }
}
