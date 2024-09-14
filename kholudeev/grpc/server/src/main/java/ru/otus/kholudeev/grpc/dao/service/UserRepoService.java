package ru.otus.kholudeev.grpc.dao.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.kholudeev.grpc.dao.exception.DaoException;
import ru.otus.kholudeev.grpc.dao.exception.EntityNotFoundException;
import ru.otus.kholudeev.grpc.dao.model.DaoUser;
import ru.otus.kholudeev.grpc.dao.repository.UserRepository;
import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRepoService {
    private final UserRepository repo;

    public Optional<DaoUser> findById(Long id) {
        try {
            return repo.findById(id);
        } catch (Exception e) {
            log.error("Ошибка получения пользователя по ID {}", id, e);
            throw new DaoException("Ошибка получения пользователя по ID", e);
        }
    }

    public DaoUser getById(Long id) {
        return findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Не найден пользователь, id - %s", id)));
    }

    public Optional<DaoUser> findByName(String name) {
        try {
            return repo.findByName(name);
        } catch (Exception e) {
            log.error("Ошибка поиска пользователя, Имя пользователя - {}", name, e);
            throw new DaoException(format("Ошибка поиска пользователя, Имя пользователя - %s", name), e);
        }
    }

    public DaoUser getByName(String name) {
        return repo.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Пользователь с именем %s не найден", name)));
    }

    public DaoUser save(DaoUser daoUser) {
        try {
            return repo.save(daoUser);
        } catch (Exception e) {
            log.error("Ошибка сохранения пользователя, логин - {}", daoUser.getEmail(), e);
            throw new DaoException(format("Ошибка сохранения пользователя, логин - %s", daoUser.getEmail()), e);
        }
    }
}
