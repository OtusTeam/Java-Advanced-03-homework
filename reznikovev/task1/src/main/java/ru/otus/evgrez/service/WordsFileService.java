package ru.otus.evgrez.service;

import java.util.List;

public interface WordsFileService {

    List<String> getWordsByFileName(String fileName);
}
