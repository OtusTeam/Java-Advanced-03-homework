package task10.fileUtils;

import java.nio.Buffer;

public interface FileService {
    void put(String key);
    void get(String key);
    void getCachedFiles();
    void removeWeakLinksAndClearCache();
}
