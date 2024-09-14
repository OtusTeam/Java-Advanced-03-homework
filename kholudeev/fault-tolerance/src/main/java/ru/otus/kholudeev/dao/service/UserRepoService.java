package ru.otus.kholudeev.dao.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.kholudeev.dao.exception.DaoException;
import ru.otus.kholudeev.dao.exception.UserNotFoundException;
import ru.otus.kholudeev.dao.model.User;
import ru.otus.kholudeev.dao.repository.UserRepository;
import ru.otus.kholudeev.exception.ParamValue;

import java.util.Map;
import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRepoService {
    private final UserRepository repo;

    public User getById(Long id) {
        return repo.findById(id).
                orElseThrow(() -> new UserNotFoundException(String.format("Не найден пользователь, id - %s", id),
                        Map.of("userField", ParamValue.valueOf("id"),
                                "userValue", ParamValue.valueOf(id))));
    }

    public User getByName(String name) {
        return repo.findByName(name).
                orElseThrow(() -> new UserNotFoundException(String.format("Не найден пользователь, name - %s", name),
                        Map.of("userField", ParamValue.valueOf("name"),
                                "userValue", ParamValue.valueOf(name))));
    }

    public Optional<User> findByLogin(String login) {
        try {
            return repo.findByLogin(login);
        } catch (Exception e) {
            log.error("Ошибка поиска пользователя, логин - {}", login, e);
            throw new DaoException(format("Ошибка поиска пользователя, логин - %s", login), e);
        }
    }

    public User getByLogin(String login) {
        return repo.findByLogin(login).
                orElseThrow(() -> new UserNotFoundException(String.format("Не найден пользователь, логин - %s", login),
                        Map.of("userField", ParamValue.valueOf("login"),
                                "userValue", ParamValue.valueOf(login))));
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
