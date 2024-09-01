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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class CircuitBreakerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private ClientRest clientRest;

    @Test
    public void circuitBreakerOpenTest() {
        var success = 5;
        var failure = 10;
        final List<ResponseEntity<String>> responses = new ArrayList<>();
        Mockito.when(clientRest.callApi())
                .thenReturn("", returnMockSuccess(success - 1))
                .thenThrow(returnMockNotSuccess(failure));
        IntStream.rangeClosed(1, success + failure)
                .forEach(it -> responses.add(testRestTemplate.getForEntity("/circuit-breaker", String.class)));
        verify(clientRest, times(10)).callApi();
        assertEquals(success + failure, responses.size());
        assertEquals(5,
                responses.stream().filter(it -> it.getStatusCode() == HttpStatus.OK).count());
        assertEquals(5,
                responses.stream().filter(it -> it.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR).count());
        assertEquals(5,
                responses.stream().filter(it -> it.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE).count());
    }

    private String[] returnMockSuccess(int count) {
        return IntStream.rangeClosed(1, count).boxed().map(it -> "").toArray(String[]::new);
    }

    private Throwable[] returnMockNotSuccess(int count) {
        Throwable[] result = new Throwable[count];
        IntStream.rangeClosed(1, count).forEach(it -> result[it - 1] = new IllegalStateException());
        return result;
    }
}
