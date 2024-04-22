package ru.skilanov.reference;

import java.lang.ref.SoftReference;
import java.util.HashMap;

public class SoftReferenceMap<K, V> extends HashMap<K, V> {

    @Override
    public V put(K key, V value) {
        return super.put((K) new SoftReference<>(key), value);
    }

    @Override
    public V get(Object key) {
        for (Entry<K, V> entry : super.entrySet()) {
            SoftReference<K> softKey = (SoftReference<K>) entry.getKey();
            K keyFound = softKey.get();
            if (keyFound != null && keyFound.equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }
}
