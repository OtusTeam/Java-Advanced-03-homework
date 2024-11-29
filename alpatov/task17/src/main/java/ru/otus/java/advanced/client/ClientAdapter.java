package ru.otus.java.advanced.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ClientAdapter implements ClientApi {

    private final RpmLimiter rpmLimiter;
    private final ClientRest clientRest;

    @Override
    @RateLimiter(name = "rps_limiter")
    public Integer callRpmLimitApi(UUID id) {
        return rpmLimiter.callRateLimitApi(id);
    }

    @Override
    @CircuitBreaker(name = "default")
    public Integer callCircuitBreakerApi(UUID id) {
        return clientRest.callApi(id);
    }
}
