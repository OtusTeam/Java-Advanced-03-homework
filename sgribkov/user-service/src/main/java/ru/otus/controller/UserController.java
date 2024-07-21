package ru.otus.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.dao.UserDao;
import ru.otus.model.User;
import ru.otus.model.UserData;
import ru.otus.model.UserIdentity;
import ru.otus.service.UserMonitoringService;


@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserDao userDao;
    private final UserMonitoringService userMonitoringService;

    @GetMapping("/{login}")
    public Mono<String> getUser(@PathVariable String login) {
        return userDao.findByLogin(login).map(User::getId);
    }

    @GetMapping("/report")
    public Flux<UserData> getUserReport() {
        return userMonitoringService.getUserReport();
    }

    @PostMapping
    public Mono<String> saveUser(@RequestBody User user) {
        return userDao.save(user)
                .doOnNext(userMonitoringService::run)
                .map(User::getId);
    }

    @PutMapping
    public Mono<String> updateUser(@RequestBody User user) {
        return userDao
                .update(user).map(User::getId);
    }

    @DeleteMapping("/{login}")
    public Mono<String> deleteUser(@PathVariable String login) {
        return userDao.delete(login)
                .doOnNext(userMonitoringService::stop);
    }
}
