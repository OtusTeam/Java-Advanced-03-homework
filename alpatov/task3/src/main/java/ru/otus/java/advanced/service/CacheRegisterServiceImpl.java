package ru.otus.java.advanced.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;
import ru.otus.java.advanced.entity.User;

import java.util.concurrent.TimeUnit;

@Service
public class CacheRegisterServiceImpl implements CacheRegisterService {

    Cache<String, User> cache = Caffeine.newBuilder()
            .expireAfterWrite(30, TimeUnit.SECONDS)
            .maximumSize(5000)
            .build();

    @Override
    public void put(User user) {
        cache.put(user.getLogin(), user);
    }

    @Override
    public boolean contains(String login) {
        return cache.asMap().containsKey(login);
    }

    @Override
    public User get(String login) {
        return cache.getIfPresent(login);
    }
}