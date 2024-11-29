package ru.otus.java.advanced.client;

import java.util.UUID;

public interface ClientApi {

    Integer callRpmLimitApi(UUID id);

    Integer callCircuitBreakerApi(UUID id);
}
