package ru.otus.java.advanced.core;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataBase {
    private static final List<User> dataBase = new ArrayList<>();

    public User save(User user) {
        user.setId(UUID.randomUUID());
        dataBase.add(user);
        return user;
    }
}