package ru.otus.services.filecache;

import java.nio.ByteBuffer;

public interface FileCache {
    void loadContent(String id, ByteBuffer content);
    String getContent(String id);
    boolean hasContent(String id);
}
