package ru.otus.dao;

import ru.otus.model.User;


public interface UserDao {
    User findByLogin(String login);
    User save(User user);
    User update(User user);
    String delete(String login);
}
