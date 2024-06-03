package ru.otus.service;

import ru.otus.model.User;
import ru.otus.model.UserData;

import java.util.List;

public interface UserMonitoringService {
    void run(User user);
    List<UserData> getUserReport();
}
