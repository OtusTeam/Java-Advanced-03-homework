package ru.otus.evgrez.cache;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WeakRefCacheTest {

    @Test
    void commonTest() {
        WeakRefCache<String, String> cache = new WeakRefCache<>();
        int size = 1_000;
        String key = null;
        for (int i = 0; i < size; i++) {
            key = "key " + i;
            cache.put(key, "value " + i);
        }
        System.gc();
        Assertions.assertTrue(cache.size() < size);
        Assertions.assertTrue(cache.containsKey("key " + (size - 1)));
        Assertions.assertEquals("value " + (size - 1), cache.getByKey("key " + (size - 1)).get());
    }

}