package com.memory.cache;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

public class FileCacheImpl extends Cache<String> {
    Logger logger = Logger.getLogger(FileCacheImpl.class.getName());

    private final String directory;

    public FileCacheImpl(String directory) {
        super();
        this.setSupplier(getSupplier());
        this.directory = directory;
    }

    private Function<CacheKey, String> getSupplier() {
        return new Function<CacheKey, String>() {
            @Override
            public String apply(CacheKey cacheKey) {
                try {
                    var url = getClass().getClassLoader()
                            .getResource(directory + "/" + ((FileCacheKey) cacheKey).getFileName());
                    Path path = Paths.get(url.toURI());

                    return Files.readString(path);
                } catch (Exception e) {
                   logger.log(Level.WARNING, e, () -> "Error while reading file");
                   return null;
                }
            }
        };
    }

    @Getter
    @AllArgsConstructor
    @EqualsAndHashCode
    @ToString
    public static class FileCacheKey implements CacheKey {
        private String fileName;
    }
}
