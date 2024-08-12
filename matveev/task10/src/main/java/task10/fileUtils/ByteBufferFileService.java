package task10.fileUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import task10.cache.Cache;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class ByteBufferFileService implements FileService {

    public Cache cache;

    public ByteBufferFileService(Cache cache) {
        this.cache = cache;
    }

    @Setter
    @Getter
    private String fileDir;
    private CustomFileReader fileReader = new CustomFileReader();

    public void put(String fileName) {
        try {
            Buffer buffer = fileReader.readByteBufferFromFile(fileName);
            cache.put(fileName, buffer);
            System.out.println("Added " + fileName + " to cache via ByteBuffer with content: " + buffer);
        } catch (Exception e) {
            System.out.println("File not found");
        }
    }

    public void get(String fileName) {
        try {
            Buffer bufferValue = cache.get(fileName);
            if (bufferValue != null) {
                CharBuffer bufferContent = StandardCharsets.UTF_8.decode((ByteBuffer) bufferValue);
                System.out.println("Get file" + fileName + " content from cache successfully, content: " + bufferContent);
            } else {
                System.out.println("File not found");
            }
        } catch (Exception e) {
            System.out.println("File not found");
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
    }
}
