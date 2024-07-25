package ru.otus.service;

import ru.otus.model.User;
import ru.otus.model.UserData;

import java.util.List;

public interface UserMonitoringService {
    void run(User user);
    boolean stop(String login);
    List<UserData> getUserReport();
}
