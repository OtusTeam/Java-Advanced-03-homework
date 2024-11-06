package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.model.User;
import ru.otus.model.UserData;
import ru.otus.service.UserService;

@Component
@RequiredArgsConstructor
public class UserClient {

    private final UserService userService;

    Flux<String> getAll() {
        return userService.getAll();
    }

    Mono<String> findByLogin(String login) {
        return userService.findByLogin(login);
    }

    Mono<String> save(User user) {
        return userService.save(user);
    }

    Mono<String> update(User user){
        return userService.update(user);
    }

    Mono<String> delete(String login) {
        return userService.delete(login);
    }

    Flux<UserData> getUserReport(){
        return userService.getUserReport();
    }

    Mono<Long> getUserAge(String login){
        return userService.getUserAge(login);
    }
}
