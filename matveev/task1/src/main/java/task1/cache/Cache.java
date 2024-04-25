package task1.cache;

public interface Cache {
    void put(String key, String value);

    String get(String key);

    void clean();

    int getCacheSize();

    void printCacheInfo();

    void printReferenceQueueInfo();
}
