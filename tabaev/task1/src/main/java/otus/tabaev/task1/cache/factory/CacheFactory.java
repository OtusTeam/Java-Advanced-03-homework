package otus.tabaev.task1.cache.factory;

import otus.tabaev.task1.cache.Cache;
import otus.tabaev.task1.cache.SoftReferencesCache;
import otus.tabaev.task1.cache.WeakReferencesCache;

public class CacheFactory {
    public static Cache<String, String> createCache(String source, Long intervalClean, Long intervalRefresh, String cacheTypeString) {
        CacheType cacheType = CacheType.fromString(cacheTypeString);
        switch (cacheType) {
            case SOFT:
                return new SoftReferencesCache(source, intervalClean, intervalRefresh);
            case WEAK:
                return new WeakReferencesCache(source, intervalClean, intervalRefresh);
            default:
                throw new IllegalArgumentException(String.format("Кэш типа: %s не поддерживается", cacheTypeString));
        }
    }
}
