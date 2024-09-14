package ru.otus.kholudeev.grpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.kholudeev.grpc.controller.UserController;

@SpringBootApplication
public class ApplicationLauncher {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationLauncher.class, args);
    }
}
