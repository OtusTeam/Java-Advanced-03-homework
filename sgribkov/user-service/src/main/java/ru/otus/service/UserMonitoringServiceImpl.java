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
import java.util.concurrent.*;

@Service
@AllArgsConstructor
public class UserMonitoringServiceImpl implements UserMonitoringService {
    private static final Logger log = LoggerFactory.getLogger(UserMonitoringServiceImpl.class);
    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
    private static final Map<UserIdentity, String> history = new ConcurrentHashMap<>();
    private static final Map<String, MonitoringRunningTask> users = new ConcurrentHashMap<>();

    @Override
    public void run(User user) {
        var userIdentity = new UserIdentity(user);

        var runMonitoring = new Runnable() {
            @Override
            public void run() {
                String ts = Instant.now().toString();
                history.put(userIdentity, ts);
            }
        };

        var stopMonitoring = new Callable<Boolean>() {
            @Override
            public Boolean call() {
                String login = history.remove(userIdentity);
                log.info("Monitoring was stopped for user: id {}, login {}",
                        userIdentity.getId(),
                        userIdentity.getLogin());
                return login != null;
            }
        };

        ScheduledFuture<?> scheduled =
                executor.scheduleAtFixedRate(runMonitoring, 0, 1, TimeUnit.SECONDS);

        var monitoringRunningTask = new MonitoringRunningTask(userIdentity, scheduled, stopMonitoring);
        users.put(userIdentity.getLogin(), monitoringRunningTask);
    }

    @Override
    public boolean stop(String login) {
        MonitoringRunningTask monitoringRunningTask = users.remove(login);
        Boolean stopResult = false;

        if (monitoringRunningTask != null) {
            try {
                Future<Boolean> future = executor.submit(monitoringRunningTask);
                stopResult = future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        } else {
            log.info("There is no monitoring process for user: {}.", login);
        }

        return stopResult;
    }

    @Override
    public List<UserData> getUserReport() {
        return history.keySet().stream()
                .map(key -> new UserData(key.getId(), key.getLogin(), history.get(key)))
                .toList();
    }

    private class MonitoringRunningTask implements Callable<Boolean> {
        private final UserIdentity userIdentity;
        private final ScheduledFuture<?> scheduledTask;
        private final Callable<Boolean> stopMonitoring;

        public MonitoringRunningTask(UserIdentity userIdentity,
                                     ScheduledFuture<?> scheduledMonitoringTask,
                                     Callable<Boolean> stopMonitoring) {
            this.userIdentity = userIdentity;
            this.scheduledTask = scheduledMonitoringTask;
            this.stopMonitoring = stopMonitoring;
        }

        @Override
        public Boolean call() {
            try {
                Boolean cancelResult = scheduledTask.cancel(true);
                Boolean deleteDataResult = stopMonitoring.call();
                return cancelResult && deleteDataResult;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }
}
