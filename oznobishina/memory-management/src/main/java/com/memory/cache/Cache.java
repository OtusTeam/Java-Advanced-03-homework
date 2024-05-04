package com.memory.cache;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Загружает содержимое, передаваемое supplier в кеш, если содержимое кеша было использовано меньше
 * 1 раза в минуту, то содежимое помещается в WeakReference и будет удалено при следующей сборке мусора.
 * Раз в минуту кеш проверяет, если содержимое ключа было очищено, ключ удаляется из кеша тоже.
 */
public abstract class Cache<R> {

    public static final long CLEAR_CACHE_PERIOD = 60000L;
    public static final long DELAY = 1000L;
    Logger logger = Logger.getLogger(Cache.class.getName());

    private Function<CacheKey, R> supplier;
    private ConcurrentHashMap<CacheKey, Reference<R>> cache = new ConcurrentHashMap<>();
    private ConcurrentHashMap<CacheKey, Integer> usageCounter = new ConcurrentHashMap<>();

    protected Cache() {
        TimerTask cleanCacheTask = new TimerTask() {
            public void run() {
                usageCounter.forEach((cacheKey, counter) -> {
                    if (counter <= 1) {
                        var value = cache.get(cacheKey);
                        if (value instanceof SoftReference<R>) {
                            cache.put(cacheKey, new WeakReference<>(value.get()));
                        }
                    } else {
                        var value = cache.get(cacheKey);
                        if (value instanceof WeakReference<R>) {
                            cache.put(cacheKey, new SoftReference<>(value.get()));
                        }
                    }
                });

                Set<CacheKey> keysToRemove = new HashSet<>();
                cache.forEach(((cacheKey, rReference) -> {
                    if (rReference.get() == null) {
                        keysToRemove.add(cacheKey);
                    }
                }));
                keysToRemove.forEach(key -> {
                    cache.remove(key);
                    usageCounter.remove(key);
                });
                logger.log(Level.INFO, "Cache cleaned up, removed " + keysToRemove.size() + " empty keys:" + keysToRemove);
            }
        };
        new Timer().scheduleAtFixedRate(cleanCacheTask, DELAY, CLEAR_CACHE_PERIOD);
    }

    public R getValue(CacheKey cacheKey) {
        R value = getValueFromCache(cacheKey);

        if (value == null) {
            logger.log(Level.INFO, "Value not found in cache, loading from file");
            loadValueIntoCache(cacheKey);
            value = getValueFromCache(cacheKey);
        }
        return value;
    }

    public void loadValueIntoCache(CacheKey cacheKey) {
        if (cacheKey == null) {
            logger.warning("Cache key is null");
            return;
        }

        var value = supplier.apply(cacheKey);
        if (value == null) {
            logger.log(Level.INFO, "Couldn't load value");
            return;
        }

        cache.put(cacheKey, new SoftReference<>(value));
        usageCounter.put(cacheKey, 0);
        logger.log(Level.INFO, "Value loaded into cache");

    }

    public R getValueFromCache(CacheKey cacheKey) {
        var cacheValue = cache.get(cacheKey);
        R value = null;
        if (cacheValue != null) {
            value = cacheValue.get();
            if (value != null) {
                usageCounter.put(cacheKey, usageCounter.get(cacheKey) + 1);
            }
            logger.log(Level.INFO, "Value loaded from cache {} times", usageCounter.get(cacheKey));
        }
        if (value != null) {
            return value;
        } else {
            logger.log(Level.INFO, "Key {} not found in cache", cacheKey);
            return null;
        }
    }

    protected void setSupplier(Function<CacheKey, R> supplier) {
        this.supplier = supplier;
    }

    public interface CacheKey {

    }
}
