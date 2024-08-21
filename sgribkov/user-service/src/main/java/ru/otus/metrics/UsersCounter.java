package ru.otus.metrics;

import ru.otus.model.User;
import ru.otus.model.UserIdentity;

public interface UsersCounter {
    void addUser(UserIdentity userIdentity);
    void subtractUser(boolean isDeleted);
}
