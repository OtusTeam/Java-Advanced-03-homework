package ru.skilanov.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skilanov.cache.Cache;
import ru.skilanov.domain.Users;
import ru.skilanov.dto.UserCreateDto;
import ru.skilanov.dto.UserDto;
import ru.skilanov.repository.UserRepository;
import ru.skilanov.utils.PasswordHashingUtil;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Cache<Long, Users> cache;

    private final String algorithm;

    public UserServiceImpl(UserRepository userRepository,
                           Cache<Long, Users> cache,
                           @Value("${password.algorithm}") String algorithm) {
        this.userRepository = userRepository;
        this.cache = cache;
        this.algorithm = algorithm;
    }

    @Transactional
    @Override
    public UserDto create(UserCreateDto dto) {
        var hashedPassword = PasswordHashingUtil.hashPassword(dto.getPassword(), algorithm);

        var mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(STRICT);

        var entity = mapper.map(dto, Users.class);
        entity.setPassword(hashedPassword);
        var savedEntity = userRepository.save(entity);

        cache.load(savedEntity.getId(), savedEntity);

        return mapper.map(savedEntity, UserDto.class);
    }
}
