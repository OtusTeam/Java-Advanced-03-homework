package ru.otus.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.model.User;
import ru.otus.model.UserData;
import ru.otus.model.UserIdentity;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class UserMonitoringServiceImpl implements UserMonitoringService {
    private static final Logger log = LoggerFactory.getLogger(UserMonitoringServiceImpl.class);
    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
    private static final Map<UserIdentity, String> history = new ConcurrentHashMap<>();

    @Override
    public void run(User user) {
        var task = new Runnable() {
            @Override
            public void run(){
                String ts = Instant.now().toString();
                history.put(new UserIdentity(user), ts);
            };
        };
        executor.scheduleAtFixedRate(task, 0, 1, TimeUnit.MILLISECONDS);
    }

    @Override
    public List<UserData> getUserReport() {
        return history.keySet().stream()
                .map(key -> new UserData(key.getId(), key.getLogin(), history.get(key)))
                .toList();
    }
}
