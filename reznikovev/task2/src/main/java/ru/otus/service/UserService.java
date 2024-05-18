package ru.otus.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.otus.exception.IdMismatchException;
import ru.otus.exception.ObjectNotFoundException;
import ru.otus.model.User;
import ru.otus.repo.UserRepository;

import static ru.otus.config.TopologyCacheManagerConfig.USER_NAME;

@Service
@CacheConfig(cacheNames = USER_NAME)
@Slf4j
@Data
public class UserService {

    private final UserRepository userRepository;

    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    @Cacheable(key = "#id")
    public User getById(Long id) {
        log.info("getById");
        return userRepository.findById(id)
                .orElseThrow(ObjectNotFoundException::new);
    }

    @CachePut(key = "#user.getId()")
    public User create(User user) {
        return userRepository.save(user);
    }

    @CacheEvict(key = "#id")
    public void delete(Long id) {
        log.info("delete");
        getById(id);
        userRepository.deleteById(id);
    }

    @CachePut(key = "#user.getId()")
    public User update(User user) {
        log.info("update");
        if (user.getId() == null) {
            throw new IdMismatchException();
        }
        getById(user.getId());
        return userRepository.save(user);
    }
}
