package com.example.otus.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import static com.example.otus.utils.MemoryMonitor.printMemoryUsage;


//Description Background memory leak.
/*@Component
public class BeanMemoryEater {

    private List<byte[]> memoryLeakList = new ArrayList<>();

    @PostConstruct
    public void init() {
        new Thread(() -> {
            try {
                while (true) {
                    printMemoryUsage("PostConstruct mem: ");
                    memoryLeakList.add(new byte[1024 * 1024 * 50]);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }


}*/
