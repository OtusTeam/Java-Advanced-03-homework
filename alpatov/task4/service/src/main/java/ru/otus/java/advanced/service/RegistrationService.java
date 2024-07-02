package ru.otus.java.advanced.service;

import ru.otus.java.advanced.core.DataBase;
import ru.otus.java.advanced.core.User;

public class RegistrationService {

    private final DataBase dataBase;

    public RegistrationService() {
        this.dataBase = new DataBase();
    }

    public User registerUser(String name) {
        User user = new User();
        user.setName(name);
        return dataBase.save(user);
    }
}