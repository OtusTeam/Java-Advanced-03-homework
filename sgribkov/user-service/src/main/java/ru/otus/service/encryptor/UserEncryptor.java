package ru.otus.service.encryptor;

import reactor.core.publisher.Mono;
import ru.otus.model.User;

public interface UserEncryptor {
    User encrypt(User user);
}
