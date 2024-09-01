package com.example.demo;

import java.util.concurrent.CompletableFuture;

public interface ClientApi {

    String callRetryApi();
    String callRateLimiterApi();
    String callRateRpmLimiterApi();
    String circuitBreakerApi();
}
