package ru.otus.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.services.filecache.SoftRefFileCache;

import java.nio.ByteBuffer;

public class SoftRefFileCacheTest {

    @DisplayName("После заполнения heap до предела значение по выбранному ключу должно отсутствовать в кэше")
    @Test
    void gcImpactOnBigContentTest() {
        //given
        String ID = "a";
        ByteBuffer CONTENT = ByteBuffer.allocate(6);

        var cache = new SoftRefFileCache();
        cache.loadContent(ID, CONTENT);

        //when
        for (int i = 0; i < 2000000; i++) {
            cache.loadContent(
                    String.valueOf(i),
                    ByteBuffer.allocate(6));
        }
        String contentById = cache.getContent(ID);

        //then
        Assertions.assertNull(contentById);
    }

    @DisplayName("После сборки мусора при незаполненном heap значение по выбранному ключу должно остаться в кэше")
    @Test
    void gcImpactOnSmallContentTest() {
        //given
        String ID = "a";
        ByteBuffer CONTENT = ByteBuffer.allocate(6);

        var cache = new SoftRefFileCache();
        cache.loadContent(ID, CONTENT);

        //when
        System.gc();
        String contentById = cache.getContent(ID);

        //then
        Assertions.assertNotNull(contentById);
    }

}
