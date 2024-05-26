package com.example.otus;

import com.example.otus.cache.Cache;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Random;
import static com.example.otus.utils.MemoryMonitor.printMemoryUsage;

@SpringBootTest
class Homework2ApplicationTests {

/*    @SneakyThrows
    @Test
    void addOneMillionValuesToCash() {
        Cache cache = new Cache();
        String user = RandomStringUtils.randomAlphanumeric(10);
        Random rand = new Random();
        int password = rand.nextInt(10);
        int counter = 0;
        while (counter < 1000000000) {
            cache.put(user, password);
            counter++;
        }
    }*/

}
