package ru.otus.service.encryptor;

import ru.otus.model.User;

public interface UserEncryptor {
    User encrypt(User user);
}
