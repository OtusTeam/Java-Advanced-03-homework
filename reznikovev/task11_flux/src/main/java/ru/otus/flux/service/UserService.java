package ru.otus.flux.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.flux.model.User;
import ru.otus.flux.repo.UserRepository;

import static ru.otus.flux.config.CacheManagerConfig.USER_CACHE;


@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    Cache cache;

    public UserService(UserRepository userRepository, @Qualifier(USER_CACHE) Cache userCache) {
        this.userRepository = userRepository;
        cache = userCache;
    }

    public Flux<User> getAll() {
        return userRepository.findAll();
    }

    public Mono<User> getById(Long id) {
        Cache.ValueWrapper valueWrapper = cache.get(id);
        if (valueWrapper != null && valueWrapper.get() != null) {
            return Mono.just((User) valueWrapper.get()).log("Cache");
        } else {
            return userRepository.findById(id)
                    .flatMap(user -> {
                        cache.put(user.getId(), user);
                        return Mono.just(user).log("Db");
                    })
                    .switchIfEmpty(Mono.empty());
        }
    }

    public Mono<User> create(User user) {
        return userRepository.save(user);
    }

    public Mono<Void> delete(Long id) {
        return userRepository.findById(id)
                .flatMap(user -> {
                    cache.evictIfPresent(id);
                    return Mono.just(user);
                })
                .flatMap(user -> userRepository.deleteById(user.getId()))
                .switchIfEmpty(Mono.empty());
    }

    public Mono<User> update(User user) {
        return userRepository.findById(user.getId())
                .flatMap(it -> {
                    cache.evictIfPresent(it.getId());
                    return Mono.just(user);
                })
                .flatMap(userRepository::save);
    }
}
