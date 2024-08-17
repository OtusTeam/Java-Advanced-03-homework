package ru.otus.core;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Hooks;

/**
 * @author Anton Ponomarev on 15.06.2024
 * @project Default (Template) Project
 */
@SpringBootApplication
@EnableWebFlux
public class UserReactorApp {
    public static void main(String[] args) {
        Hooks.onOperatorDebug();
        SpringApplication.run(UserReactorApp.class, args);
    }
}