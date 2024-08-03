package ru.otus.service.usermonitoring;

import reactor.core.publisher.Flux;
import ru.otus.model.User;
import ru.otus.model.UserData;
import ru.otus.model.UserIdentity;

public interface UserMonitoringService {
    UserIdentity run(User user);
    boolean stop(String login);
    Flux<UserData> getUserReport();
}
