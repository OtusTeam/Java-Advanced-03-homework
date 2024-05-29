package com.example.outofmemorytask.service;

import com.example.outofmemorytask.model.UserEntity;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Anton Ponomarev on 13.05.2024
 * @project Java-Advanced-homework
 */
public interface UserService {

  CompletableFuture<UserEntity> create(UserEntity userEntity) throws ExecutionException, InterruptedException;
}
