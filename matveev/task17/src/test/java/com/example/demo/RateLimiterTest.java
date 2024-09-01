package com.example.demo;

import com.example.demo.clients.ClientRest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RateLimiterTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private ClientRest clientRest;

    @Test
    public void testNotLimit() {
        var countRequest = 20;
        Mockito.when(clientRest.callApi()).thenReturn("");
        IntStream.rangeClosed(1, countRequest)
                .parallel()
                .forEach(it -> {
                    var response = testRestTemplate.getForEntity("/rate-limiter", String.class);
                    assertEquals(HttpStatus.OK, response.getStatusCode());
                });
        verify(clientRest, times(countRequest)).callApi();
    }


    @Test
    public void testRpsLimit() {
        //limit-for-period=20
        //limit-refresh-period=1s
        var limit = 20;
        var countRequest = limit + 5;
        Mockito.when(clientRest.callApi()).thenReturn("");

        var responses = new CopyOnWriteArrayList<ResponseEntity<String>>();

        IntStream.rangeClosed(1, countRequest)
                .parallel()
                .forEach(it -> responses.add(testRestTemplate.getForEntity("/rate-limiter", String.class)));

        assertEquals(countRequest, responses.size());
        assertEquals(limit,
                responses.stream().filter(it -> it.getStatusCode() == HttpStatus.OK).count());
        assertEquals(limit,
                countRequest - responses.stream().filter(it -> it.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS).count());
        verify(clientRest, times(20)).callApi();
    }

    @Test
    public void testRpmLimit() {
        //limit-for-period=30
        //limit-refresh-period=1m
        var countRequestRps = 20;
        var countRequestRpm = 30;
        var repeat = 3;
        Mockito.when(clientRest.callApi()).thenReturn("");
        var responses = new CopyOnWriteArrayList<ResponseEntity<String>>();

        IntStream.rangeClosed(1, repeat)
                .parallel()
                .forEach(it -> {
                            IntStream.rangeClosed(1, countRequestRps)
                                    .forEach(it1 -> responses.add(testRestTemplate.getForEntity("/rate-rpm-limiter", String.class)));
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
