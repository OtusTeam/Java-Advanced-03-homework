package ru.skilanov.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skilanov.domain.Users;
import ru.skilanov.dto.UserDto;
import ru.skilanov.repository.UserRepository;

import java.util.*;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private Map<Long, Users> userCache = new WeakHashMap<>();

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDto insert(UserDto dto) {
        var mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(STRICT);

        var entity = mapper.map(dto, Users.class);
        var savedEntity = userRepository.save(entity);

        userCache.put(savedEntity.getId(), savedEntity);

        return mapper.map(savedEntity, UserDto.class);
    }

}
