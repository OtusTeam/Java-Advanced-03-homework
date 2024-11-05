package com.example.circuitbreaker.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.circuitbreaker.client.ClientAdapter;
import com.example.circuitbreaker.client.ClientRest;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControllerTest {

    private static final String CUSTOMER_URL = "/customer/age/3";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private ClientRest clientRest;

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    @Autowired
    private RateLimiterRegistry rateLimiterRegistry;

    @BeforeEach
    public void setUp() {
        circuitBreakerRegistry.circuitBreaker("default").reset();
        rateLimiterRegistry.remove("default");
        rateLimiterRegistry.remove("rpm_limiter");

    }

    @Test
    void circuitBreakerCountOpenTest() {
        var numberSuccessfulFirst = 5;
        var numberNotSuccessful = 7;

        List<ResponseEntity<String>> responses = new ArrayList<>();

        Mockito.when(clientRest.callApi())
                .thenReturn(18, returnMockSuccess(numberSuccessfulFirst - 1))
                .thenThrow(returnMockNotSuccess(numberNotSuccessful));

        IntStream.rangeClosed(1, numberSuccessfulFirst + numberNotSuccessful)
                .forEach(it -> responses.add(testRestTemplate.getForEntity(CUSTOMER_URL, String.class)));

        verify(clientRest, times(10)).callApi();

        assertEquals(numberSuccessfulFirst + numberNotSuccessful, responses.size());
        assertEquals(numberSuccessfulFirst,
                responses.stream().filter(it -> it.getStatusCode() == HttpStatus.OK).count());
        assertEquals(5,
                responses.stream().filter(it -> it.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR).count());
        assertEquals(2,
                responses.stream().filter(it -> it.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE).count());

    }

    private Integer[] returnMockSuccess(int count) {
        return IntStream.rangeClosed(1, count).boxed().toArray(Integer[]::new);
    }

    private Throwable[] returnMockNotSuccess(int count) {
        Throwable[] result = new Throwable[count];
        IntStream.rangeClosed(1, count).forEach(it -> result[it - 1] = new IllegalStateException());
        return result;
    }
}