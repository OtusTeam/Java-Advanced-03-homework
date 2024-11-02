package com.example.circuitbreaker.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ClientAdapter {

    @Autowired
    private RPMRateLimitedSearch rpmRateLimitedSearch;

    @RateLimiter(name = "default")
    @CircuitBreaker(name="default")
    public Integer circuitBreakerApi() {
        log.info("Call api");
        return rpmRateLimitedSearch.callRateLimiterApi();
    }
}
