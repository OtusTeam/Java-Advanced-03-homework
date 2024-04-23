package cache.model.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.ref.SoftReference;
import java.net.URISyntaxException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Anton Ponomarev on 23.04.2024
 * @project Java-Advanced-homework
 */
class CacheDataSoftRefTest {
    CacheDataSoftRefImpl cacheDataSoftRef;

    @BeforeEach
    void init() {
        cacheDataSoftRef = new CacheDataSoftRefImpl(new HashMap<>());
    }

    @Test
    void uploadFileSuccess() throws URISyntaxException {
        var filePath = getClass().getClassLoader().getResource("files/Address.txt");
        File file = new File(filePath.toURI());
        assertTrue(file.exists());
        cacheDataSoftRef.uploadFile("Address.txt", file);
        assertTrue(cacheDataSoftRef.containsFile("Address.txt"));
        assertTrue(cacheDataSoftRef.getDataFile("Address.txt") instanceof SoftReference<?>);
        assertEquals("[address.txt test data]", String.valueOf(cacheDataSoftRef.getDataFile("Address.txt").get()));
    }

}