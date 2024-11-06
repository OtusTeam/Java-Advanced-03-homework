package ru.otus.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserControllerResilienceTest {

    @MockBean
    UserClient userClient;

    @Autowired
    private WebTestClient webClient;

    @Test
    public void getUserAgeRpsLimit() {

        int requestsLimit = 20;
        int requestsNum = requestsLimit + 5;
        String userLogin = "vasya";

        Mockito
                .when(userClient.getUserAge(userLogin))
                .thenReturn(Mono.just(42L));

        var responses = new CopyOnWriteArrayList<HttpStatusCode>();

        IntStream.rangeClosed(1, requestsNum).parallel()
                .forEach(ic ->
                        webClient.get()
                                .uri("/users/{login}/age", userLogin)
                                .accept(MediaType.APPLICATION_JSON)
                                .exchange()
                                .returnResult(Long.class)
                                .consumeWith(c -> responses.add(c.getStatus()))
                );

        List<HttpStatusCode> responsesOk = responses.stream()
                .filter(status -> status == HttpStatus.OK)
                .toList();

        assertEquals(requestsNum, responses.size());
        assertEquals(requestsLimit, responsesOk.size());
    }

    @Test
    @Timeout(61)
    public void getUserAgeRpmLimit() {

        int requestsLimit = 30;
        int requestsNum = requestsLimit + 5;
        String userLogin = "vasya";

        Mockito
                .when(userClient.getUserAge(userLogin))
                .thenReturn(Mono.just(42L));

        var responses = new CopyOnWriteArrayList<HttpStatusCode>();

        IntStream.rangeClosed(1, requestsNum).parallel()
                .forEach(ic -> {
                    Executor executor = CompletableFuture.delayedExecutor(ic, TimeUnit.SECONDS);
                    CompletableFuture.runAsync(
                            () -> webClient.get()
                                    .uri("/users/{login}/age", userLogin)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .exchange()
                                    .returnResult(Long.class)
                                    .consumeWith(c -> responses.add(c.getStatus())),
                            executor)
                            .join();
                });

        List<HttpStatusCode> responsesOk = responses.stream()
                .filter(status -> status == HttpStatus.OK)
                .toList();

        assertEquals(requestsNum, responses.size());
        assertEquals(requestsLimit, responsesOk.size());
    }

    @Test
    public void getUserAgeCircuitBreaker() {

        String rightUserLogin = "vasya";
        String wrongUserLogin = "vasya2";

        Map<Integer, String> requestParams = Map.of(
                0, wrongUserLogin,
                1000, wrongUserLogin,
                2000, wrongUserLogin,
                3000, wrongUserLogin,
                4000, rightUserLogin
        );

        Mockito
                .when(userClient.getUserAge(rightUserLogin))
                .thenReturn(Mono.just(42L));

        Mockito
                .when(userClient.getUserAge(wrongUserLogin))
                .thenReturn(Mono.error(new RuntimeException("No user")));

        var responses = new CopyOnWriteArrayList<HttpStatusCode>();

        requestParams.entrySet().stream().parallel()
                .forEach(params -> {
                    Executor executor = CompletableFuture.delayedExecutor(params.getKey(), TimeUnit.MILLISECONDS);
                    CompletableFuture.runAsync(
                            () -> webClient.get()
                                    .uri("/users/{login}/age", params.getValue())
                                    .accept(MediaType.APPLICATION_JSON)
                                    .exchange()
                                    .returnResult(Long.class)
                                    .consumeWith(c -> responses.add(c.getStatus())),
                            executor)
                            .join();
                });

        List<HttpStatusCode> expectedResponses = Arrays.stream(
                new HttpStatusCode[]{
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        HttpStatus.SERVICE_UNAVAILABLE,
                        HttpStatus.OK
                }).toList();


        System.out.println(responses.toString());

        assertEquals(requestParams.size(), responses.size());
        assertEquals(expectedResponses, responses);
    }
}
