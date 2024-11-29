package ru.otus.java.advanced.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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
    public UserDto register(UserDto userDto) {
        userDto.setPassword(HashPasswordUtil.hash(userDto.getPassword(), hashAlgorithm));
        User user = userMapper.toEntity(userDto);
        user.setPassword(HashPasswordUtil.hash(user.getPassword(), hashAlgorithm));
        return userMapper.toDto(userRepository.save(user));
    }
}
