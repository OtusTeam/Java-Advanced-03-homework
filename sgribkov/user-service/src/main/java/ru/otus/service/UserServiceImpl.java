package ru.otus.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.metrics.UsersCounter;
import ru.otus.model.UserIdentity;
import ru.otus.repository.UserRepository;
import ru.otus.model.User;
import ru.otus.model.UserData;
import ru.otus.service.usermonitoring.UserMonitoringService;
import ru.otus.service.encryptor.UserEncryptor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final UserMonitoringService userMonitoringService;

    @Autowired
    private final UserEncryptor userEncryptor;

    @Autowired
    private final UsersCounter usersCounter;

    @Override
    public Flux<String> getAll() {
        return userRepository.findAll()
                .map(User::getId);
    }

    @Override
    public Mono<String> findByLogin(String login) {
        return userRepository.findById(login)
                .map(User::getId)
                .switchIfEmpty(
                        Mono.error(
                                new RuntimeException(
                                        String.format("User with login: %s not found", login))));
    }

    @Override
    @Transactional
    public Mono<String> save(User user) {
        return Mono.defer(() -> Mono.just(userEncryptor.encrypt(user)))
                .flatMap(u -> {
                    u.setNew(true);
                    Mono<User> savedUser = userRepository.save(u);
                    return savedUser
                            .doOnNext(us -> {
                                UserIdentity userIdentity = userMonitoringService.run(us);
                                usersCounter.addUser(userIdentity);
                                log.info("Saved user: {} with encrypted password: {}",
                                        us.getId(),
                                        us.getPassword());
                            })
                            .map(User::getId);
                });
    }

    @Override
    @Transactional
    public Mono<String> update(User user) {
        return Mono.defer(() -> Mono.just(userEncryptor.encrypt(user)))
                .flatMap(u -> {
                    u.setNew(false);
                    Mono<User> updatedUser = userRepository.save(u);

                    log.info("Updated user: {} with encrypted password: {}",
                            u.getId(),
                            u.getPassword());

                    return updatedUser.map(User::getId);
                });
    }

    @Override
    @Transactional
    public Mono<String> delete(String login) {
        return userRepository.deleteById(login)
                .thenReturn(login)
                .doOnNext(u -> {
                    boolean isDeleted = userMonitoringService.stop(login);
                    usersCounter.subtractUser(isDeleted);
                    log.info("Deleted user: {}", login);
                });
    }

    @Override
    public Flux<UserData> getUserReport() {
        return userMonitoringService.getUserReport();
    }
}
