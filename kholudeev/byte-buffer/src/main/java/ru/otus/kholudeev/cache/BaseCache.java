package ru.otus.kholudeev.cache;

import ru.otus.kholudeev.exception.CacheKeyDoesntExistsException;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.lang.String.format;

public abstract class BaseCache implements Cacheable<String, Reference<ByteBuffer>> {
    protected Map<String, Reference<ByteBuffer>> cache;
    protected ReferenceQueue<ByteBuffer> referenceQueue;

    protected BaseCache() {
        cache = new HashMap<>();
        referenceQueue = new ReferenceQueue<>();
    }

    @Override
    public Reference<ByteBuffer> get(String key) throws CacheKeyDoesntExistsException {
        Reference<ByteBuffer> bufferReference = cache.get(key);
        if (Objects.isNull(bufferReference)){
            throw new CacheKeyDoesntExistsException(format("Key %s dosn't exists", key));
        }
        return bufferReference;
    }

    public ByteBuffer getValue(String key) throws CacheKeyDoesntExistsException {
        return get(key).get();
    }

    @Override
    public void add(String key, Reference<ByteBuffer> value) {
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

    public abstract void put(String fileName, ByteBuffer read);
}
