package ru.otus.evgrez.service;

import ru.otus.evgrez.cache.SoftRefCache;
import ru.otus.evgrez.cache.WeakRefCache;
import ru.otus.evgrez.component.CacheStrategy;
import ru.otus.evgrez.dao.WordsFileDao;

import java.util.List;

public class WordsFileFactory {

    private WordsFileFactory() {
    }

    public static WordsFileService getWordsFileServiceWeakRef(String path) {
        var dao = new WordsFileDao(path);
        var cache = new WeakRefCache<String, List<String>>();
        var storage = new CacheStrategy<>(cache, dao::getWords);
        return new WordsFileServiceSimple(storage);
    }

    public static WordsFileService getWordsFileServiceSoftRef(String path) {
        var dao = new WordsFileDao(path);
        var cache = new SoftRefCache<String, List<String>>();
        var storage = new CacheStrategy<>(cache, dao::getWords);
        return new WordsFileServiceSimple(storage);
    }
}
