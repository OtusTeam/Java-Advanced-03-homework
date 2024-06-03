package ru.otus.services.filecache;

public interface FileCache {
    void loadContent(String id, String content);
    String getContent(String id);
    boolean hasContent(String id);
}
