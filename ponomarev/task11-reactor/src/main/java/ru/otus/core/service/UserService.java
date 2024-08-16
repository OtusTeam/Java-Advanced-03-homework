package ru.otus.core.service;

import reactor.core.publisher.Mono;
import ru.otus.core.model.UserEntity;

/**
 * @author Anton Ponomarev on 13.05.2024
 * @project Java-Advanced-homework
 */
public interface UserService {

    Mono<UserEntity> create(UserEntity userEntity);
}
