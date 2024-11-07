package ru.otus.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.model.User;
import ru.otus.model.UserData;

@Component
@RequiredArgsConstructor
public class UserClientResilienceAdapter {

    @Autowired
    private final UserClient userClient;

    @Autowired
    private final UserClientRpmLimiter userClientRpmLimiter;

    Flux<String> getAll() {
        return userClient.getAll();
    }

    Mono<String> findByLogin(String login) {
        return userClient.findByLogin(login);
    }

    Mono<String> save(User user) {
        return userClient.save(user);
    }

    Mono<String> update(User user){
        return userClient.update(user);
    }

    Mono<String> delete(String login) {
        return userClient.delete(login);
    }

    Flux<UserData> getUserReport(){
        return userClient.getUserReport();
    }

    @RateLimiter(name = "getUserAgeRateLimiterSec")
    @CircuitBreaker(name = "getUserAgeCircuitBreaker")
    Mono<Long> getUserAge(String login) {
        return userClientRpmLimiter.getUserAge(login);
    }
}
