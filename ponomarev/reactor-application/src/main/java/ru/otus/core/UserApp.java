package ru.otus.core;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Hooks;

/**
 * @author Anton Ponomarev on 15.06.2024
 * @project Default (Template) Project
 */
@SpringBootApplication
@EnableWebFlux
public class UserApp {
    public static void main(String[] args) {
        Hooks.onOperatorDebug();
        SpringApplication.run(UserApp.class, args);
    }
}