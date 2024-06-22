package ru.otus.dao;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.db.repository.UserRepository;
import ru.otus.db.sessionmanager.TransactionManager;
import ru.otus.model.User;
import ru.otus.service.encryptor.UserEncryptor;

@Service
@AllArgsConstructor
public class DbUserDao implements UserDao {
    private static final Logger log = LoggerFactory.getLogger(DbUserDao.class);

    private final TransactionManager transactionManager;
    private final UserRepository userRepository;
    @Autowired
    private final UserEncryptor userEncryptor;

    @Override
    public User findByLogin(String login) {
        return userRepository.findById(login).orElseThrow(() ->
                new RuntimeException(
                        String.format("User with login: %s not found", login)));
    }

    @Override
    public User save(User user) {
        return transactionManager.doInTransaction(() -> {
            User userEncrypted = userEncryptor.encrypt(user);
            userEncrypted.setNew(true);
            var savedUser = userRepository.save(userEncrypted);
            log.info("Saved user: {} with encrypted password: {}",
                     savedUser.getId(),
                     savedUser.getPassword());
            return savedUser;
        });
    }

    @Override
    public User update(User user) {
        return transactionManager.doInTransaction(() -> {
            User userEncrypted = userEncryptor.encrypt(user);
            userEncrypted.setNew(false);
            var updatedUser = userRepository.save(user);
            log.info("Updated user: {} with encrypted password: {}",
                     updatedUser.getId(),
                     updatedUser.getPassword());
            return updatedUser;
        });
    }

    @Override
    public String delete(String login) {
        return transactionManager.doInTransaction(() -> {
            userRepository.deleteById(login);
            log.info("Deleted user: {}", login);
            return login;
        });
    }
}
