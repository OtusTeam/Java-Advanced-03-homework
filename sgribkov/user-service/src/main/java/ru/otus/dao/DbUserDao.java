package ru.otus.dao;

import lombok.AllArgsConstructor;
import org.apache.commons.math3.analysis.function.Identity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.db.repository.UserRepository;
import ru.otus.model.User;
import ru.otus.service.encryptor.UserEncryptor;

@Service
@AllArgsConstructor
public class DbUserDao implements UserDao {
    private static final Logger log = LoggerFactory.getLogger(DbUserDao.class);
    private final UserRepository userRepository;
    @Autowired
    private final UserEncryptor userEncryptor;

    @Override
    public Mono<User> findByLogin(String login) {
        return userRepository.findById(login).switchIfEmpty(
                Mono.error(
                        new RuntimeException(
                                String.format("User with login: %s not found", login))));
    }

    @Override
    @Transactional
    public Mono<User> save(User user) {
        User userEncrypted = userEncryptor.encrypt(user);
        userEncrypted.setNew(true);
        return userRepository.save(userEncrypted)
                .doOnNext(u ->
                        log.info("Saved user: {} with encrypted password: {}",
                                u.getId(),
                                u.getPassword()));
    }

    @Override
    @Transactional
    public Mono<User> update(User user) {
        User userEncrypted = userEncryptor.encrypt(user);
        userEncrypted.setNew(false);
        return userRepository.save(userEncrypted)
                .doOnNext(u ->
                        log.info("Updated user: {} with encrypted password: {}",
                                u.getId(),
                                u.getPassword()));
    }

    @Override
    @Transactional
    public Mono<String> delete(String login) {
        return userRepository.deleteById(login)
                .thenReturn(login)
                .doOnNext(l -> log.info("Deleted user: {}", login));
    }
}
