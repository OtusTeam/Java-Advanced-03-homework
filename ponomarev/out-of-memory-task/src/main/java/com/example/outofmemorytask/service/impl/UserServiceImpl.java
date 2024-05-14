package com.example.outofmemorytask.service.impl;

import com.example.outofmemorytask.model.UserEntity;
import com.example.outofmemorytask.repo.UserDao;
import com.example.outofmemorytask.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    private final UserDao userDao;
    private final Executor executor;
    private static final List<UserEntity> allUserEntities = new ArrayList<>();
    @Override
    public CompletableFuture<UserEntity> create(UserEntity userEntity) throws ExecutionException, InterruptedException {
        CompletableFuture<UserEntity> future = CompletableFuture.supplyAsync(() -> {
//                    List<UserEntity> allUserEntities = userDao.findAll();
                    allUserEntities.addAll(userDao.findAll()) ;
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (!allUserEntities.contains(userEntity)) {
                        userEntity.incrementVersion();
                        return userDao.save(userEntity);
                    } else {
                        UserEntity entity = allUserEntities.get(allUserEntities.indexOf(userEntity));
                        entity.incrementVersion();
                        userDao.save(entity);
                        return entity;
                    }
                }
                );
//                , executor);
        return future;
    }
}
