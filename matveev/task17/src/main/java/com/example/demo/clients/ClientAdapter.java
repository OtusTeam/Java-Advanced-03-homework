package com.example.demo.clients;

import com.example.demo.ClientApi;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ClientAdapter implements ClientApi {
    private final RPMRateLimited rpmRateLimited;

    public ClientAdapter(RPMRateLimited rpmRateLimitedSearch) {
        this.rpmRateLimited = rpmRateLimitedSearch;
    }

    @RateLimiter(name = "default")
    @CircuitBreaker(name = "default")
    public String getAge(Integer id) {
        log.info(String.format("Call ClientAdapter.getAge(%s)", id));
        return rpmRateLimited.getAge(id);
    }

}
