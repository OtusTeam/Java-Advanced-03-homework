package ru.otus.kholudeev.dao.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.kholudeev.dao.exception.DaoException;
import ru.otus.kholudeev.dao.exception.UserNotFoundException;
import ru.otus.kholudeev.dao.model.User;
import ru.otus.kholudeev.dao.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRepoService {
    private final UserRepository repo;

    public Optional<User> findById(Long id) {
        try {
            return repo.findById(id);
        } catch (Exception e) {
            log.error("Ошибка получения пользователя по ID {}", id, e);
            throw new DaoException("Ошибка получения пользователя по ID", e);
        }
    }

    public User getById(Long id) {
        return findById(id).orElseThrow(() ->
                new UserNotFoundException(String.format("Не найден пользователь, id - %s", id)));
    }

    public Optional<User> findByName(String name) {
        try {
            return repo.findByName(name);
        } catch (Exception e) {
            log.error("Ошибка поиска пользователя, Имя пользователя - {}", name, e);
            throw new DaoException(format("Ошибка поиска пользователя, Имя пользователя - %s", name), e);
        }
    }

    public User getByName(String name) {
        return repo.findByName(name)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("Пользователь с именем %s не найден", name)));
    }

    public Optional<User> findByLogin(String login) {
        try {
            return repo.findByLogin(login);
        } catch (Exception e) {
            log.error("Ошибка поиска пользователя, логин - {}", login, e);
            throw new DaoException(format("Ошибка поиска пользователя, логин - %s", login), e);
        }
    }

    public List<User> saveAll(List<User> users) {
        try {
            return repo.saveAll(users);
        } catch (Exception e) {
            String logins = users.stream()
                    .map(User::getLogin)
                    .map(Objects::toString)
                    .collect(Collectors.joining(", "));

            log.error("Ошибка сохранения пользователей, список логинов - {}", logins, e);
            throw new DaoException(format("Ошибка сохранения пользователей, список логинов - %s", logins), e);
        }
    }

    public User save(User user) {
        try {
            return repo.save(user);
        } catch (Exception e) {
            log.error("Ошибка сохранения пользователя, логин - {}", user.getLogin(), e);
            throw new DaoException(format("Ошибка сохранения пользователя, логин - %s", user.getLogin()), e);
        }
    }

    public void deleteByLogin(String login) {
        try {
            repo.deleteByLogin(login);
        } catch (Exception e) {
            log.error("Ошибка удаления пользователя, логин - {}", login, e);
            throw new DaoException(format("Ошибка удаления пользователя, логин - %s", login), e);
        }
    }
}
