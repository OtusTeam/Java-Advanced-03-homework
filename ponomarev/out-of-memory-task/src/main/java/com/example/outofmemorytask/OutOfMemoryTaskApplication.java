package com.example.outofmemorytask;

import com.example.outofmemorytask.emulator.WebEmulator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class OutOfMemoryTaskApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(OutOfMemoryTaskApplication.class, args);
        WebEmulator webEmulator = applicationContext.getBean(WebEmulator.class);
        webEmulator.runThreads();
    }
}
