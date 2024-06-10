package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppMemoryLeak {
    public static void main(String[] args) {
        SpringApplication.run(AppMemoryLeak.class, args);
    }
}
