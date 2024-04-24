package cache.model.impl;

import cache.model.AbstractCacheData;

import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.util.HashMap;

/**
 * @author Anton Ponomarev on 22.04.2024
 * @project memory-management(Otus Java Developer. Advanced)
 */
public class CacheDataSoftRefImpl extends AbstractCacheData<String, SoftReference> {
    public CacheDataSoftRefImpl(HashMap<String, SoftReference> dataMap) {
        super(dataMap);
    }

    @Override
    public SoftReference uploadFile(String key, File value) {
        try {
            return dataMap.put(key, new SoftReference<>((Files.readAllLines(value.toPath()))));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(EXCEPTION_MESSAGE);
        }
        return null;
    }
}
