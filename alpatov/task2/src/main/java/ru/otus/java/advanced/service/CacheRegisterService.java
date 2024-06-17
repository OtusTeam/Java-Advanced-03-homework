package ru.otus.java.advanced.service;

import ru.otus.java.advanced.entity.User;

public interface CacheRegisterService {

    void put(User user);

    boolean contains(String login);

    User get(String login);
}
