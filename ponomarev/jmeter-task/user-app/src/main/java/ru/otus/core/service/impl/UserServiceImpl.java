package ru.otus.core.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.core.model.UserEntity;
import ru.otus.core.repo.UserRepository;
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

    @Override
    @Transactional
    public UserEntity create(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }
}
