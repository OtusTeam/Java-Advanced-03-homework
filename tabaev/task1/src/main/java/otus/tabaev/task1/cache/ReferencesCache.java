package otus.tabaev.task1.cache;


import otus.tabaev.task1.cache.exception.CacheLoadException;

import java.lang.ref.Reference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class ReferencesCache<K, V> implements Cache<K, V> {

    protected Map<K, Reference<V>> cache = new ConcurrentHashMap<>();

    public V get(K key) throws CacheLoadException {
        if (this.containsKey(key)) {
            return cache.get(key).get();
        } else {
            return this.load(key);
        }
    }

    public boolean containsKey(K key) {
        Reference<V> reference = cache.get(key);
        return reference != null && reference.get() != null;
    }

    public void clear() {
        cache.clear();
    }

    public void cleanUp() {
        cache.entrySet().removeIf(entry -> {
            Reference<V> reference = entry.getValue();
            return reference == null || reference.get() == null;
        });
    }

    public void refresh() {
        cache.keySet().forEach(key -> {
            try {
                load(key);
            } catch (CacheLoadException e) {
                System.err.println("Ошибка при обновлении кэша. " + e.getMessage());
                cache.remove(key);
            }
        });
    }

}
