package ru.otus.services.filecache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class WeakRefFileCache implements FileCache {
    private final Map<String, WeakReference<String>> files = new HashMap<>();

    @Override
    public void loadContent(String id, String content) {
        files.put(id, new WeakReference<String>(content));
    }

    @Override
    public String getContent(String id) {
        if (files.containsKey(id)) {
            return files.get(id).get();
        } else {
            throw new RuntimeException("No file content for " + id);
        }
    }

    @Override
    public boolean hasContent(String id) {
        return files.containsKey(id);
    }
}
