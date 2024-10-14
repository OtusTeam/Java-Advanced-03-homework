package ru.otus.java.advanced.service;

import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public Integer getAge(UUID id) {
        return ThreadLocalRandom.current().nextInt(0, 101);
    }
}
