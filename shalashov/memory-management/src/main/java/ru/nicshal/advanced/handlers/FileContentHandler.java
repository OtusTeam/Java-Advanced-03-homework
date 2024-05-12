package ru.nicshal.advanced.handlers;

import ru.nicshal.advanced.caches.ReferenceCache;
import ru.nicshal.advanced.caches.SoftReferenceCacheImpl;
import ru.nicshal.advanced.caches.WeakReferenceCacheImpl;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static ru.nicshal.advanced.utils.ApplicationConstant.SOFT_REFERENCE_TYPE;

public class FileContentHandler {

    ReferenceCache cache;

    public FileContentHandler(String referenceType) {
        this.cache = SOFT_REFERENCE_TYPE.equals(referenceType) ? new SoftReferenceCacheImpl() : new WeakReferenceCacheImpl();
    }

    public void printFileContents(String filePath) {
        if (!cache.containsKey(filePath)) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
                StringBuilder buffer = new StringBuilder();
                String rec;
                while ((rec = bufferedReader.readLine()) != null) {
                    buffer.append(rec);
                    buffer.append("\n");
                }
                cache.put(filePath, buffer.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(cache.get(filePath));
        System.out.println("---");
    }

}