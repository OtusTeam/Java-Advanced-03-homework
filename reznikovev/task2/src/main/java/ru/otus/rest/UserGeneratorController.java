package ru.otus.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.model.User;
import ru.otus.service.UserService;

import java.util.Objects;
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

    @GetMapping("/{from}/{to}")
    public HttpStatus generate(@PathVariable Integer from, @PathVariable Integer to) {
        Objects.requireNonNull(from, () -> "from is null");
        Objects.requireNonNull(to, () -> "to is null");
        Assert.isTrue(from < to, "from>=to");
        CompletableFuture.runAsync(() -> {
            int i = 0;
            int j = from;
            for (; j <= to; j++) {
                i++;
                var user = new User();
                user.setLogin("login_" + j);
                user.setPassword("pass_" + j);
                userService.create(user);
                if (i % 1000 == 0) {
                    log.info("Created {} records", i);
                }
            }
            log.info("Created {} records", i);
            log.info("Generation is completed");
        });
        return HttpStatus.OK;
    }
}
