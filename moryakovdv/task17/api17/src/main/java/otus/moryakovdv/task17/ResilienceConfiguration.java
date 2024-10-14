package otus.moryakovdv.task17;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

//@Configuration 
@Getter

@Slf4j
public class ResilienceConfiguration {

	

    private final static RateLimiterRegistry rateLimitsRegistry = RateLimiterRegistry.ofDefaults();
	

    private final static CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.ofDefaults();

    @Bean
    public RateLimiterRegistry registerRateLimiter() {
    	
    	log.info("Rate limiter creation");
    	
        RateLimiterConfig rps = RateLimiterConfig
        		.custom()
                .limitForPeriod(20)
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .timeoutDuration(Duration.ofMillis(500))
                .build();

        RateLimiterConfig rpm = RateLimiterConfig
        		.custom()
                .limitForPeriod(30)
                .limitRefreshPeriod(Duration.ofMinutes(1))
                .timeoutDuration(Duration.ofMillis(1000))
                .build();

        rateLimitsRegistry.rateLimiter("RPS", rps);
        rateLimitsRegistry.rateLimiter("RPM", rpm);
        
        return rateLimitsRegistry;
    }
    

    @Bean
    public CircuitBreakerRegistry registerCircuitBreaker() {
    	
    	log.info("Circuit breaker  creation");

    	
        CircuitBreakerConfig cbConfig = CircuitBreakerConfig
        		.custom()
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.TIME_BASED)
                
                .slidingWindowSize(10)
                
                //без этой инструкции защита не применится
                .minimumNumberOfCalls(10)
                
                .waitDurationInOpenState(Duration.ofSeconds(1))
                .failureRateThreshold(50)
                .permittedNumberOfCallsInHalfOpenState(3)
                .build();
        
        circuitBreakerRegistry.circuitBreaker("CBR", cbConfig);
        
        return circuitBreakerRegistry;
    }

		
	
}
