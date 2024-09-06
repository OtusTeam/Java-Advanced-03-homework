package com.example.demo.clients;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RPMRateLimited {
    private final ClientRest clientRest;

    public RPMRateLimited(ClientRest clientRest) { this.clientRest = clientRest; }

    @RateLimiter(name = "rpm_limiter")
    public String getAge(Integer id) {
        return clientRest.callApi(id);
    }
}
