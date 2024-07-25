package ru.otus.java.advanced.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.java.advanced.entity.User;
import ru.otus.java.advanced.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserRegisterServiceImpl implements UserRegisterService {

    private final UserRepository userRepository;
    private final CacheRegisterService cacheRegisterService;

    @Override
    public User register(User user) {
        if (!cacheRegisterService.contains(user.getLogin())) {
            User savedUser = userRepository.save(user);
            cacheRegisterService.put(savedUser);
        }
        return cacheRegisterService.get(user.getLogin());
    }
}
