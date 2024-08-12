package task10.cache;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WeakRefCache implements Cache {
    private Map<String, WeakReference<Buffer>> cache = new HashMap<>();
    private final ReferenceQueue<Buffer> referenceQueue = new ReferenceQueue<>();

    public void put(String key, Buffer value) {
        WeakReference<Buffer> weakValue = new WeakReference<>(value, referenceQueue);
        cache.put(key, weakValue);
    }

    public Buffer get(String key) {
        return cache.get(key) != null ? cache.get(key).get() : null;
    }

    @Override
    public void clean() {
        cache.clear();
    }

    @Override
    public int getCacheSize() {
        return cache.size();
    }

    @Override
    public void printCacheInfo() {
        List<String> cachedFiles = this.cache.entrySet().stream()
                .map(entry -> "\nKey: " + entry.getKey() + ", value: " + entry.getValue().get())
                .collect(Collectors.toList());
        if (cachedFiles.isEmpty()) {
            System.out.println("Weak cache is empty.");
        } else {
            System.out.println(cachedFiles);
        }
    }

    @Override
    public void printReferenceQueueInfo() {
        System.out.println("RefQueue links:");
        if (cache.isEmpty()) System.out.println(referenceQueue.poll());
        for (int i = 0; i < cache.size(); i++) {
            System.out.println(i + ") " + referenceQueue.poll());
        }
    }
}
