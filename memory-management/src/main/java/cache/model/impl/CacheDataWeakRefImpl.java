package cache.model.impl;

import cache.model.AbstractCacheData;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.file.Files;
import java.util.HashMap;

/**
 * @author Anton Ponomarev on 22.04.2024
 * @project memory-management(Otus Java Developer. Advanced)
 */
public class CacheDataWeakRefImpl extends AbstractCacheData<String, WeakReference> {
    public CacheDataWeakRefImpl(HashMap<String, WeakReference> dataMap) {
        super(dataMap);
    }

    @Override
    public WeakReference uploadFile(String key, File value) {
        try {
            return dataMap.put(key, new WeakReference<>((Files.readAllLines(value.toPath()))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
