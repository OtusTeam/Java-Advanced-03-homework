package ru.otus.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.services.filecache.WeakRefFileCache;

import java.nio.ByteBuffer;

public class WeakRefFileCacheTest {

    @DisplayName("После сборки мусора значение по выбранному ключу должно отсутствовать в кэше")
    @Test
    void gcImpactOnContentTest() {
        //given
        String ID = "a";
        ByteBuffer CONTENT = ByteBuffer.allocate(6);

        var cache = new WeakRefFileCache();
        cache.loadContent(ID, CONTENT);

        //when
        CONTENT = null;
        System.gc();
        String contentById = cache.getContent(ID);

        //then
        Assertions.assertNull(contentById);
    }

}
