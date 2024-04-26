package ru.skilanov.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.skilanov.repository.FileRepository;

import java.util.Map;
import java.util.WeakHashMap;

@Service
@ConditionalOnProperty(prefix = "reference", name = "type", havingValue = "weak")
public class WeakReferenceCacheServiceImpl<K, V> implements CacheService<K, V> {
    private final Map<K, V> cache = new WeakHashMap<>();

    private final FileRepository<K, V> fileService;

    public WeakReferenceCacheServiceImpl(FileRepository<K, V> fileService) {
        this.fileService = fileService;
    }

    @Override
    public V get(String path, K key) {
        loadFileIfWasNotCached(path, key);
        return cache.get(key);
    }

    @Override
    public void load(String path, K key) {
        loadFileIfWasNotCached(path, key);
    }

    private void loadFileIfWasNotCached(String path, K key) {
        if (!cache.containsKey(key)) {
            V value = fileService.readFile(key, path);
            cache.put(key, value);
        }
    }
}
