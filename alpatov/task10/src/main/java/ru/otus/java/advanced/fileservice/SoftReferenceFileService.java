package ru.otus.java.advanced.fileservice;

import ru.otus.java.advanced.cache.SoftReferenceCache;
import ru.otus.java.advanced.filereader.AbstractFileReader;

public class SoftReferenceFileService extends AbstractFileService {

    public SoftReferenceFileService(AbstractFileReader fileReader) {
        super(fileReader);
        super.cache = new SoftReferenceCache<>();
    }
}
