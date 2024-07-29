package ru.otus.core.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public UserEntity create(UserEntity userEntity) {
        userEntity.setPassword(hashPasswordService.hashPassword(userEntity.getPassword(), "MD5"));
        return userRepository.save(userEntity);
    }
}
