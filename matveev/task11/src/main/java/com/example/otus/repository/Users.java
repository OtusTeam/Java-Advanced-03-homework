package com.example.otus.repository;

import com.example.otus.model.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface Users extends ReactiveCrudRepository<User, Long> {
    @Query("select * from users where users.login=:login and users.password=:password")
    Mono<User> login(String login, String password);

    @Query("select * from users where users.login=:login")
    Flux<User> findByLogin(String login);

}
