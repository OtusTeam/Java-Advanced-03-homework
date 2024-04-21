package task1.fileUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import task1.cache.Cache;
import java.util.concurrent.TimeUnit;

public class FileService {

    public Cache cache;

    public FileService(Cache cache) {
        this.cache = cache;
    }

    @Setter
    @Getter
    private String fileDir;
    private CustomFileReader fileReader = new CustomFileReader();

    public void put(String fileName) {
        String fileContent = fileReader.readFile(fileDir + "/" + fileName);
        cache.put(fileName, fileContent);
        System.out.println("Added " + fileName + " to cache with content: " + fileContent);
    }

    public void get(String fileName) {
        String fileContentFromCache = cache.get(fileName);
        if (fileContentFromCache != null) {
            System.out.println("Get file" + fileName + " content from cache successfully, content: " + fileContentFromCache);
        } else {
            System.out.println("File not found: " + fileName);
        }
    }

    public void getCachedFiles() {
        cache.printCacheInfo();
    }

    @SneakyThrows
    public void removeWeakLinksAndClearCache() {
        int cacheSize = cache.getCacheSize();
        System.out.println("Cache size before: " + cacheSize);
        System.out.println("-----------------------");
        cache.printReferenceQueueInfo();
        System.out.println("Run gc()");
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("-----------------------");
        cache.printReferenceQueueInfo();
        System.out.println("-----------------------");
        System.out.println("Check cache after gc():");
        getCachedFiles();
        System.out.println("-----------------------");
        System.out.println("Running clear cache");
        cache.clean();
        System.out.println("-----------------------");
        System.out.println("Cache size after: " + cache.getCacheSize());
        System.out.println("-----------------------");
    }
}
