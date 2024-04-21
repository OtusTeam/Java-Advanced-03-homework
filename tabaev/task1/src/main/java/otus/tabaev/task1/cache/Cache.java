package otus.tabaev.task1.cache;

import otus.tabaev.task1.cache.exception.CacheLoadException;
import otus.tabaev.task1.cache.exception.DataSourceException;

public interface Cache<K, V> {

    V load(K key) throws CacheLoadException;

    V get(K key) throws CacheLoadException;

    boolean containsKey(K key);

    void clear();

    void refresh();

    void stop();

    <S> void setDataSource(S source) throws DataSourceException;

}
