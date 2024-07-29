package ru.otus.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.model.User;
import ru.otus.model.UserData;


public interface UserService {
    Flux<String> getAll();
    Mono<String> findByLogin(String login);
    Mono<String> save(User user);
    Mono<String> update(User user);
    Mono<String> delete(String login);
    Flux<UserData> getUserReport();
}
