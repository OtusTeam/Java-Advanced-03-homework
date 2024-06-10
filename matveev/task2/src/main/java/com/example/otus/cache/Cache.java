
package com.example.otus.cache;

import org.springframework.stereotype.Component;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.*;
import java.util.stream.Collectors;
import static com.example.otus.utils.MemoryMonitor.printMemoryUsage;


@Component("Cache")
public class Cache {

    private final List<byte[]> memoryLeakList = new ArrayList<>();
    private final Map<String, Integer> cache = new HashMap<>();
    public void put(String key, Integer value) {
        //printMemoryUsage("Before added to cache: ");
        cache.put(key, value);
        //cache.put(key, new Integer[value * 999]);
        //memoryLeakList.add(new byte[1024 * 1024 * 100]);// add 100 MB
        printMemoryUsage("After added to cache: ");
        //System.out.println("Memory leak array size is :" + memoryLeakList.size());
    }

    public Integer get(String key) {
        //memoryLeakList.add(new byte[1024 * 1024 * 50]);
        return cache.get(key) != null ? cache.get(key) : null;
    }

}
