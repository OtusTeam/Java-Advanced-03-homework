package otus.tabaev.task1.cache.factory;

public enum CacheType {
    SOFT,
    WEAK;

    public static CacheType fromString(String type) {
        if (type != null) {
            for (CacheType cacheType : CacheType.values()) {
                if (cacheType.name().equalsIgnoreCase(type)) {
                    return cacheType;
                }
            }
        }
        throw new IllegalArgumentException(String.format("Кэш типа: %s не поддерживается", type));
    }
}
