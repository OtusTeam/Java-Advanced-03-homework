package ru.otus.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;
import ru.otus.model.UserIdentity;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class UsersCounterImpl implements UsersCounter {
    private final AtomicInteger counter;

    public UsersCounterImpl(MeterRegistry registry) {
        this.counter = registry.gauge("users.number", new AtomicInteger(0));
    }

    public void addUser(UserIdentity userIdentity) {
        if (userIdentity != null) {
            this.counter.incrementAndGet();
        }
    }

    public void subtractUser(boolean isDeleted) {
        if (isDeleted) {
            this.counter.decrementAndGet();
        }
    }
}
