package ru.otus.evgrez.cache;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public abstract class AbstractRefCache<K, V> implements Cache<K, V> {

    private final Map<Reference<K>, V> storage;

    private final ReferenceQueue<K> referenceQueue;

    protected AbstractRefCache(Map<Reference<K>, V> storage,
                               ReferenceQueue<K> referenceQueue) {
        this.storage = storage;
        this.referenceQueue = referenceQueue;
    }

    @Override
    public boolean containsKey(K key) {
        keyIsNotNull(key);
        removeObsoleteReferences();
        return storage.containsKey(getRefKey(key));
    }

    @Override
    public int size() {
        removeObsoleteReferences();
        return storage.size();
    }

    private static <K> void keyIsNotNull(K key) {
        requireNonNull(key, () -> "Cache key cannot be null");
    }

    @Override
    public Optional<V> getByKey(K key) {
        keyIsNotNull(key);
        removeObsoleteReferences();
        V value = storage.get(getRefKey(key));
        if (value != null) {
            return Optional.of(value);
        }
        return Optional.empty();
    }

    @Override
    public Optional<V> put(K key, V value) {
        keyIsNotNull(key);
        removeObsoleteReferences();
        storage.put(getRefKey(key), value);
        return Optional.ofNullable(storage.get(getRefKey(key)));
    }

    protected Reference<K> getRefKey(K key) {
        return getRefKey(key, referenceQueue);
    }

    protected abstract Reference<K> getRefKey(K key, ReferenceQueue<K> referenceQueue);

    public void removeObsoleteReferences() {
        while (true) {
            var key = referenceQueue.poll();
            if (key == null) {
                break;
            }
            storage.remove(key);
        }
    }

    @Override
    public String toString() {
        return "RefCache{" +
                "storage=" + storage +
                ", referenceQueue=" + referenceQueue +
                '}';
    }
}
