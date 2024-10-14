package ru.otus.java.advanced.client;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class RateLimiterTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private ClientRest clientRest;

    @Test
    void testNotLimit() {
        int countRequest = 20;
        Mockito.when(clientRest.callApi(Mockito.any())).thenReturn(ThreadLocalRandom.current().nextInt(0, 101));

        IntStream
                .rangeClosed(1, countRequest)
                .parallel()
                .forEach(request -> {
                    var response = testRestTemplate.getForEntity(String.format("/api/v1/user/%s/age", UUID.randomUUID()), Integer.class);
                    assertEquals(HttpStatus.OK, response.getStatusCode());
                });

        verify(clientRest, times(countRequest)).callApi(Mockito.any());
    }

    @Test
    void testRpsLimit() {
        int limit = 20;
        int countRequest = limit + 20;
        Mockito.when(clientRest.callApi(Mockito.any())).thenReturn(ThreadLocalRandom.current().nextInt(0, 101));
        CopyOnWriteArrayList<ResponseEntity<Integer>> responses = new CopyOnWriteArrayList<>();
        IntStream
                .rangeClosed(1, countRequest)
                .parallel()
                .forEach(request -> responses.add(testRestTemplate.getForEntity(String.format("/api/v1/user/%s/age", UUID.randomUUID()), Integer.class)));
        assertEquals(countRequest, responses.size());
        assertEquals(limit, responses.stream().filter(response -> response.getStatusCode() == HttpStatus.OK).count());
        assertEquals(limit, countRequest - responses.stream().filter(response -> response.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS).count());
        verify(clientRest, times(20)).callApi(Mockito.any());
    }

    @Test
    void testRpmLimit() {
        int countRequestRps = 20;
        int countRequestRpm = 30;
        int repeat = 2;

        Mockito.when(clientRest.callApi(Mockito.any())).thenReturn(ThreadLocalRandom.current().nextInt(0, 101));
        CopyOnWriteArrayList<ResponseEntity<Integer>> responses = new CopyOnWriteArrayList<>();

        IntStream.rangeClosed(1, repeat)
                .forEach(i -> {
                    IntStream.rangeClosed(1, countRequestRps)
                            .forEach(request ->
                                    responses.add(testRestTemplate.getForEntity(String.format("/api/v1/user/%s/age", UUID.randomUUID()), Integer.class)));
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }}
                );
        assertEquals(countRequestRps * repeat, responses.size());
        assertEquals(countRequestRpm,
                responses.stream().filter(response -> response.getStatusCode() == HttpStatus.OK).count());
        assertEquals(countRequestRps * repeat - countRequestRpm,
                responses.stream().filter(response -> response.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS).count());
        verify(clientRest, times(countRequestRpm)).callApi(Mockito.any());
    }

    @Test
    void circuitBreakerTest() {
        int success = 5;
        int failure = 10;
        List<ResponseEntity<Integer>> responses = new ArrayList<>();

        Mockito.when(clientRest.callApi(Mockito.any()))
                .thenReturn(1, returnMockSuccess(success - 1))
                .thenThrow(returnMockNotSuccess(failure));

        IntStream.rangeClosed(1, success + failure)
                .forEach(it -> responses.add(testRestTemplate.getForEntity(String.format("/api/v2/user/%s/age", UUID.randomUUID()), Integer.class)));
        verify(clientRest, times(10)).callApi(Mockito.any());
        assertEquals(success + failure, responses.size());
        assertEquals(5,
                responses.stream().filter(response -> response.getStatusCode() == HttpStatus.OK).count());
        assertEquals(5,
                responses.stream().filter(response -> response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR).count());
        assertEquals(5,
                responses.stream().filter(response -> response.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE).count());
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