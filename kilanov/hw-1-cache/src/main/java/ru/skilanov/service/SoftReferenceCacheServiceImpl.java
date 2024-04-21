package ru.skilanov.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.skilanov.reference.SoftReferenceMap;
import ru.skilanov.repository.FileRepository;

import java.util.Map;

@Service
@ConditionalOnProperty(prefix = "reference", name = "type", havingValue = "soft")
public class SoftReferenceCacheServiceImpl<K, V> implements CacheService<K, V> {
    private final Map<K, V> cache = new SoftReferenceMap<>();

    private final FileRepository<K, V> fileService;

    public SoftReferenceCacheServiceImpl(FileRepository<K, V> fileService) {
        this.fileService = fileService;
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void load(String path, K key) {
        if (!cache.containsKey(key)) {
            V value = fileService.readFile(key, path);
            cache.put(key, value);
        }
    }
}
