package com.example.circuitbreaker.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.circuitbreaker.client.ClientRest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RPMRateLimiterTest {
    private static final String CUSTOMER_URL = "/customer/age/3";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private ClientRest clientRest;

    @Test
    void testNotRpmLimit_30rpm_only30executed() {
        var countRequestRps = 15;
        var countRequestRpm = 30;
        var repeat = 2;

        Mockito.when(clientRest.callApi()).thenReturn(18);

        var responses = new CopyOnWriteArrayList<ResponseEntity<String>>();

        IntStream.rangeClosed(1, repeat)
                .forEach(it -> {
                            IntStream.rangeClosed(1, countRequestRps)
                                    .forEach(it1 ->
                                            responses.add(testRestTemplate.getForEntity(CUSTOMER_URL, String.class)));
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
        assertEquals(countRequestRps * repeat, responses.size());
        assertEquals(countRequestRpm,
                responses.stream().filter(it -> it.getStatusCode() == HttpStatus.OK).count());
        assertEquals(countRequestRps * repeat - countRequestRpm,
                responses.stream().filter(it -> it.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS).count());
        verify(clientRest, times(30)).callApi();
    }
}