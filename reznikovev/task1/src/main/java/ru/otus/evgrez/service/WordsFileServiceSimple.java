package ru.otus.evgrez.service;

import ru.otus.evgrez.component.CacheStrategy;

import java.util.List;

public class WordsFileServiceSimple implements WordsFileService {

    private final CacheStrategy<String, List<String>> cacheStrategy;

    public WordsFileServiceSimple(CacheStrategy<String, List<String>> cacheStrategy) {
        this.cacheStrategy = cacheStrategy;
    }

    @Override
    public List<String> getWordsByFileName(String fileName) {
        return cacheStrategy.getByKey(fileName).orElse(List.of());
    }
}
