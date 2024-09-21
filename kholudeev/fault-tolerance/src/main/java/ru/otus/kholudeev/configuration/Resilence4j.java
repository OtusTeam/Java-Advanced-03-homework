package ru.otus.kholudeev.configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@NoArgsConstructor
public class Resilence4j {
    @Getter
    private final static RateLimiterRegistry registry = RateLimiterRegistry.ofDefaults();
    private final static CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.ofDefaults();

    @Bean
    public static RateLimiterRegistry registerRateLimiter() {
        RateLimiterConfig rpsConfig = RateLimiterConfig.custom()
                .limitForPeriod(20)
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .timeoutDuration(Duration.ofMillis(0))
                .build();

        RateLimiterConfig rpmConfig = RateLimiterConfig.custom()
                .limitForPeriod(30)
                .limitRefreshPeriod(Duration.ofMinutes(1))
                .timeoutDuration(Duration.ofMillis(0))
                .build();

        registry.rateLimiter("rpsRateLimiter", rpsConfig);
        registry.rateLimiter("rpmRateLimiter", rpmConfig);
        return registry;
    }

    @Bean
    public static CircuitBreakerRegistry registerCircuitBreaker() {
        CircuitBreakerConfig cbConfig = CircuitBreakerConfig.custom()
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.TIME_BASED)
                .slidingWindowSize(10)
                .minimumNumberOfCalls(15)
                .waitDurationInOpenState(Duration.ofSeconds(2))
                .failureRateThreshold(50)
                .permittedNumberOfCallsInHalfOpenState(3)
                .build();
        circuitBreakerRegistry.circuitBreaker("test", cbConfig);
        return circuitBreakerRegistry;
    }
}
