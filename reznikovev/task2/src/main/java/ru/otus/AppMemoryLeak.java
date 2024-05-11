package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("ru.otus")
@EntityScan("ru.otus")
public class AppMemoryLeak {
    public static void main(String[] args) {
        SpringApplication.run(AppMemoryLeak.class, args);
    }
}
