package ru.otus.java.advanced.client;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RpmLimiter {

    private final ClientRest clientRest;

    @RateLimiter(name = "rpm_limiter")
    public Integer callRateLimitApi(UUID id) {
        return clientRest.callApi(id);
    }
}
