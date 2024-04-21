package otus.tabaev.task1.cache;

import otus.tabaev.task1.cache.ReferencesCache;
import otus.tabaev.task1.cache.exception.CacheLoadException;
import otus.tabaev.task1.cache.exception.DataSourceException;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WeakReferencesCache extends ReferencesCache<String, String> {

    private String source;
    private final ScheduledExecutorService scheduler;

    public WeakReferencesCache(String source, Long intervalClean, Long intervalRefresh) {
        this.scheduler = Executors.newScheduledThreadPool(2);
        this.source = source;
        scheduleCacheCleaning(intervalClean);
        scheduleCacheRefresh(intervalRefresh);
    }

    @Override
    public String load(String key) throws CacheLoadException {
        String pathToFile = source + "/" + key;
        String fileContent;
        try {
            fileContent = Files.readString(Paths.get(pathToFile));
        } catch (IOException e) {
            throw new CacheLoadException(String.format("Ошибка. Файл по пути: %s не найден", pathToFile), e);
        }
        cache.put(key, new WeakReference<>(fileContent));
        return fileContent;
    }

    @Override
    public void stop() {
        scheduler.shutdown();
    }

    @Override
    public <S> void setDataSource(S source) throws DataSourceException {
        if (source instanceof String pathToDirectory) {
            File directory = new File(pathToDirectory);
            if (directory.isDirectory()) {
                this.source = pathToDirectory;
                this.clear();
            } else {
                throw new DataSourceException(String.format("Указанный источник: %s не является директорией", source));
            }
        }
    }

    public void scheduleCacheRefresh(long interval) {
        scheduler.scheduleAtFixedRate(this::refresh, 0, interval, TimeUnit.MILLISECONDS);
    }

    public void scheduleCacheCleaning(long interval) {
        scheduler.scheduleAtFixedRate(this::cleanUp, 0, interval, TimeUnit.MILLISECONDS);
    }

}
