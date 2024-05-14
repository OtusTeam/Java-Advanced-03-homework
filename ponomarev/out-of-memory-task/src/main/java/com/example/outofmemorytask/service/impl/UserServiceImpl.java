package com.example.outofmemorytask.service.impl;

import com.example.outofmemorytask.model.UserEntity;
import com.example.outofmemorytask.repo.UserRepository;
import com.example.outofmemorytask.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/**
 * @author Anton Ponomarev on 13.05.2024
 * @project Java-Advanced-homework
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Executor executor;

    @Override
    public CompletableFuture<UserEntity> create(UserEntity userEntity) throws ExecutionException, InterruptedException {
        CompletableFuture<UserEntity> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (!userRepository.existsByUserNameAndUserData(userEntity.getUserName(), userEntity.getUserData())) {
                userEntity.incrementVersion();
                return userRepository.save(userEntity);
            } else {
                UserEntity entity = userRepository.findByUserName(userEntity.getUserName());
                entity.incrementVersion();
                userRepository.save(entity);
                return entity;
            }
        }, executor);
        return future;
    }
}
