package ru.otus.kholudeev.cache;

import ru.otus.kholudeev.exception.CacheKeyDoesntExistsException;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

public abstract class BaseCache implements Cacheable<String, Reference<String>> {
    protected Map<String, Reference<String>> cache;
    protected ReferenceQueue<String> referenceQueue;

    protected BaseCache() {
        cache = new HashMap<>();
        referenceQueue = new ReferenceQueue<>();
    }

    @Override
    public Reference<String> get(String key) throws CacheKeyDoesntExistsException {
        if (cache.containsKey(key))
            return cache.get(key);
        else throw new CacheKeyDoesntExistsException(format("Key %s dosn't exists", key));
    }

    public String getValue(String key) throws CacheKeyDoesntExistsException {
        return get(key).get();
    }

    @Override
    public void add(String key, Reference<String> value) {
        cache.put(key, value);
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public int getSize() {
        return cache.size();
    }

    @Override
    public void showCacheInfo() {
        List<String> elements = this.cache.entrySet().stream()
                .map(entry -> "\nKey: " + entry.getKey() + ", value: " + entry.getValue().get())
                .toList();
        if (elements.isEmpty()) {
            System.out.println("Cache is empty");
        } else {
            System.out.println(elements);
        }
    }

    public abstract void put(String fileName, String read);
}
