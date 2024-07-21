package ru.otus.dao;

import reactor.core.publisher.Mono;
import ru.otus.model.User;


public interface UserDao {
    Mono<User> findByLogin(String login);
    Mono<User> save(User user);
    Mono<User> update(User user);
    Mono<String> delete(String login);
}
