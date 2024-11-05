package com.example.circuitbreaker.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RPMRateLimitedSearch {

    @Autowired
    private final ClientRest clientRest;

    public RPMRateLimitedSearch(ClientRest clientRest) {
        this.clientRest = clientRest;
    }

    @RateLimiter(name = "rpm_limiter")
    public Integer callRateLimiterApi() {
        log.info("Call rate limit api");
        return clientRest.callApi();
    }
}
