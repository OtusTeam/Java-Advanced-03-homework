package com.example.demo.clients;

import com.example.demo.ClientApi;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class ClientAdapter implements ClientApi {
    private final RPMRateLimitedSearch rpmRateLimitedSearch;
    private final ClientRest clientRest;

    public ClientAdapter(ClientRest clientRest, RPMRateLimitedSearch rpmRateLimitedSearch) {
        this.clientRest = clientRest;
        this.rpmRateLimitedSearch = rpmRateLimitedSearch;
    }

    @Override
    @Retry(name = "default", fallbackMethod = "fallback")
    //@Retry(name = "throwingException")
    public String callRetryApi() {
        log.info("Call retry api*");
        return clientRest.callApi();
    }

    @Override
    @RateLimiter(name = "default")
    public String callRateLimiterApi() {
        log.info("Call rate limit api");
        return clientRest.callApi();
    }

    @Override
    @RateLimiter(name = "default")
    public String callRateRpmLimiterApi() {
        return clientRest.callApi();
    }

    @Override
    @CircuitBreaker(name = "default")
    public String circuitBreakerApi() {
        log.info("Call CircuitBreaker api ");
        return clientRest.callApi();
    }

}
