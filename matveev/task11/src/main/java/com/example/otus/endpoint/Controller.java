package com.example.otus.endpoint;

import com.example.otus.model.User;
import com.example.otus.repository.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class Controller {

    @Autowired
    private Users users;

    @PostMapping(value = "/login")
    public Mono<ResponseEntity<User>> login(@RequestBody User user) {
        return users.login(user.getLogin(), user.getPassword()).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/create")
    public Mono<User> create(@RequestBody User user) {
            return users.save(user);
    }

}
