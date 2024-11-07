package ru.otus.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.EntryRemovedEvent;
import io.github.resilience4j.core.registry.EntryReplacedEvent;
import io.github.resilience4j.core.registry.RegistryEventConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ResilienceConfig {

    @Bean
    public RegistryEventConsumer<CircuitBreaker> circuitBreakerEventConsumer() {
        return new RegistryEventConsumer<CircuitBreaker>() {
            @Override
            public void onEntryAddedEvent(EntryAddedEvent<CircuitBreaker> entryAddedEvent) {
                entryAddedEvent.getAddedEntry().getEventPublisher()
                        .onFailureRateExceeded(event -> log.error("circuit breaker {} failure rate {} on {}",
                                event.getCircuitBreakerName(), event.getFailureRate(), event.getCreationTime())
                        )
                        .onSlowCallRateExceeded(event -> log.error("circuit breaker {} slow call rate {} on {}",
                                event.getCircuitBreakerName(), event.getSlowCallRate(), event.getCreationTime())
                        )
                        .onCallNotPermitted(event -> log.error("circuit breaker {} call not permitted {}",
                                event.getCircuitBreakerName(), event.getCreationTime())
                        )
                        .onError(event -> log.error("circuit breaker {} error with duration {}s",
                                event.getCircuitBreakerName(), event.getElapsedDuration().getSeconds())
                        )
                        .onStateTransition(
                                event -> log.warn("circuit breaker {} state transition from {} to {} on {}",
                                        event.getCircuitBreakerName(), event.getStateTransition().getFromState(),
                                        event.getStateTransition().getToState(), event.getCreationTime())
                        );
            }
            @Override
            public void onEntryRemovedEvent(EntryRemovedEvent<CircuitBreaker> entryRemoveEvent) {
                entryRemoveEvent.getRemovedEntry().getEventPublisher()
                        .onFailureRateExceeded(event -> log.debug("Circuit breaker event removed {}",
                                event.getCircuitBreakerName()));
            }
            @Override
            public void onEntryReplacedEvent(EntryReplacedEvent<CircuitBreaker> entryReplacedEvent) {
                entryReplacedEvent.getNewEntry().getEventPublisher()
                        .onFailureRateExceeded(event -> log.debug("Circuit breaker event replaced {}",
                                event.getCircuitBreakerName()));
            }
        };
    }
}
