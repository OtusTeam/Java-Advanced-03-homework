package ru.otus.controller;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserClientRpmLimiter {

    @Autowired
    private final UserClient userClient;

    @RateLimiter(name = "getUserAgeRateLimiterMin")
    public Mono<Long> getUserAge(String login) {
        return userClient.getUserAge(login);
    }
}
