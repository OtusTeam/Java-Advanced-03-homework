package ru.otus.java.advanced.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.otus.java.advanced.dto.UserDto;
import ru.otus.java.advanced.entity.User;
import ru.otus.java.advanced.mapper.UserMapper;
import ru.otus.java.advanced.repository.UserRepository;
import ru.otus.java.advanced.util.HashPasswordUtil;

@Service
@RequiredArgsConstructor
public class UserRegisterServiceImpl implements UserRegisterService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Value("${hash.algorithm}")
    private String hashAlgorithm;

    @Override
    public Mono<UserDto> register(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user.setPassword(HashPasswordUtil.hash(user.getPassword(), hashAlgorithm));
        return userRepository.save(user)
                .map(userMapper::toDto);
    }
}
