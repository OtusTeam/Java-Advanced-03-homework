package ru.otus.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.model.User;
import ru.otus.service.UserService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/generator/user")
@Slf4j
public class UserGeneratorController {

    private final UserService userService;

    @Autowired
    public UserGeneratorController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public HttpStatus generate() {
        CompletableFuture.runAsync(() -> {
            int size = 18_000;
            for (int i = 0; i < size; i++) {
                var user = new User();
                user.setLogin("login_" + i);
                user.setPassword("pass_" + i);
                userService.create(user);
                if (i % 1000 == 0) {
                    log.info("Created {} records", i);
                }
            }
            log.info("Created {} records", size);
        });
        return HttpStatus.OK;
    }

}
