package ru.otus.core.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.core.model.UserEntity;
import ru.otus.core.repo.UserRepository;
import ru.otus.core.service.HashPasswordService;
import ru.otus.core.service.UserService;

/**
 * @author Anton Ponomarev on 13.05.2024`
 * @project Java-Advanced-homework
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final HashPasswordService hashPasswordService;

    @Override
    public Mono<UserEntity> create(UserEntity userEntity) {
        userEntity.setPassword(hashPasswordService.hashPassword(userEntity.getPassword(), "MD5"));
        return userRepository.save(userEntity);
    }

    @Override
    public Mono<UserEntity> findUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Flux<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }
}
