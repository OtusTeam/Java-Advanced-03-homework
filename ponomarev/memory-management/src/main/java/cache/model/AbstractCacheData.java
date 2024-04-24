package cache.model;

import java.io.File;
import java.util.Map;

/**
 * @author Anton Ponomarev on 22.04.2024
 * @project memory-management(Otus Java Developer. Advanced)
 */
public abstract class AbstractCacheData<String, Reference> {
    protected final Map<String, Reference> dataMap;
    protected final java.lang.String EXEPTION_MESSAGE = "Dо время загрузки файла в кэш произошла ошибка";

    public AbstractCacheData(Map<String, Reference> dataMap) {
        this.dataMap = dataMap;
    }

    public abstract Reference uploadFile(String key, File value);

    public boolean containsFile(String key) {
        return dataMap.containsKey(key);
    }

    public Reference getDataFile(String key) {
        return dataMap.get(key);
    }
}
