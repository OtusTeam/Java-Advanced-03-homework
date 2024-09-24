package ru.otus.java.advanced.service;

import ru.otus.java.advanced.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

    User save(User user);

    Optional<User> findById(UUID id);
}
