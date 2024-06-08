package ru.otus.java.advanced.service;

import org.springframework.stereotype.Service;
import ru.otus.java.advanced.entity.User;

import java.util.HashMap;
import java.util.Map;

@Service
public class CacheRegisterServiceImpl implements CacheRegisterService {

    private final Map<String, User> cache = new HashMap<>();

    @Override
    public void put(User user) {
        cache.put(user.getLogin(), user);
    }

    @Override
    public boolean contains(String login) {
        return cache.containsKey(login);
    }

    @Override
    public User get(String login) {
        return cache.get(login);
    }
}