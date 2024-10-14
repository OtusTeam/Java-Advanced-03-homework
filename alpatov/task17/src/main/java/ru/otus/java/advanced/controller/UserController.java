package ru.otus.java.advanced.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.java.advanced.client.ClientAdapter;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final ClientAdapter clientAdapter;

    @GetMapping("api/v1/user/{id}/age")
    public ResponseEntity<Integer> getAgeV1(@PathVariable UUID id) {
        log.info("get age {}", id);
        return ResponseEntity.ok(clientAdapter.callRpmLimitApi(id));
    }

    @GetMapping("api/v2/user/{id}/age")
    public ResponseEntity<Integer> getAgeV2(@PathVariable UUID id) {
        log.info("get age {}", id);
        return ResponseEntity.ok(clientAdapter.callCircuitBreakerApi(id));
    }
}
