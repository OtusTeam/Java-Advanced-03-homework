package cache.model.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.ref.WeakReference;
import java.net.URISyntaxException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Anton Ponomarev on 23.04.2024
 * @project Java-Advanced-homework
 */
class CacheDataWeakRefTest {
    CacheDataWeakRefImpl cacheDataWeakRef;

    @BeforeEach
    void init() {
        cacheDataWeakRef = new CacheDataWeakRefImpl(new HashMap<>());
    }

    @Test
    void uploadFileSuccess() throws URISyntaxException {
        var filePath = getClass().getClassLoader().getResource("files/Address.txt");
        File file = new File(filePath.toURI());
        assertTrue(file.exists());
        cacheDataWeakRef.uploadFile("Address.txt", file);
        assertTrue(cacheDataWeakRef.containsFile("Address.txt"));
        assertTrue(cacheDataWeakRef.getDataFile("Address.txt") instanceof WeakReference<?>);
        assertEquals("[address.txt test data]", String.valueOf(cacheDataWeakRef.getDataFile("Address.txt").get()));
    }
}