package com.example.demo;

import com.example.demo.clients.RPMRateLimitedSearch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.CompletableFuture;

@RestController
public class Controller {
    private final ClientApi clientApi;
    private final RPMRateLimitedSearch rpmRateLimitedSearch;


    public Controller(ClientApi clientApi, RPMRateLimitedSearch rpmRateLimitedSearch) {
        this.clientApi = clientApi;
        this.rpmRateLimitedSearch = rpmRateLimitedSearch;

    }

    @GetMapping("/retry")
    public String retryApi() {
        return clientApi.callRetryApi();
    }

    @GetMapping("/rate-limiter")
    public String rateLimiterApi() {
        return clientApi.callRateLimiterApi();
    }

    @GetMapping("/rate-rpm-limiter")
    public String rateRmpLimiterApi() {
        return rpmRateLimitedSearch.callRateLimiterApi();
    }

    @GetMapping("/circuit-breaker")
    public String circuitBreakerApi() {
        return clientApi.circuitBreakerApi();
    }

}
