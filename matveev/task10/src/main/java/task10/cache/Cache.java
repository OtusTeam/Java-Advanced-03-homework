package task10.cache;

import java.nio.Buffer;

public interface Cache {
    void put(String key, Buffer value);

    Buffer get(String key);

    void clean();

    int getCacheSize();

    void printCacheInfo();

    void printReferenceQueueInfo();
}
