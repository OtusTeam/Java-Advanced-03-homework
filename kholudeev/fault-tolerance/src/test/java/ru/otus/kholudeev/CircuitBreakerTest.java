package ru.otus.kholudeev;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.otus.kholudeev.configuration.Resilence4j;
import ru.otus.kholudeev.dao.model.User;
import ru.otus.kholudeev.dao.service.UserRepoService;
import ru.otus.kholudeev.dto.response.UserResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CircuitBreakerTest {
    private final User user = User.builder()
            .id(1L)
            .age(30)
            .name("Ivan Ivanov")
            .login("ivan30")
            .password("querty1234")
            .creationDate(LocalDateTime.now())
            .build();
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private Resilence4j resilence4j;
    @MockBean
    private UserRepoService userRepoService;

    @Test
    void circuitBreakerOpenTest() {
        var successCount = 7;
        var internalServerErrorCount = 8;
        var cbOpenErrorCount = 3;

        CircuitBreaker circuitBreaker = resilence4j.registerCircuitBreaker().circuitBreaker("test");

        final List<ResponseEntity<UserResponse>> responses = new ArrayList<>();
        Mockito.when(userRepoService.getById(anyLong()))
                .thenReturn(user, returnMockSuccess(successCount - 1))
                .thenThrow(returnMockNotSuccess(internalServerErrorCount + cbOpenErrorCount));

        IntStream.rangeClosed(1, successCount + internalServerErrorCount)
                .forEach(it -> responses.add(testRestTemplate.getForEntity(String.format("/authorization/user/cb/%s", it), UserResponse.class)));

        Awaitility.await()
                .atMost(13, TimeUnit.SECONDS)
                .until(() -> circuitBreaker.getState().equals(CircuitBreaker.State.OPEN));

        IntStream.rangeClosed(1, cbOpenErrorCount)
                .forEach(it -> responses.add(testRestTemplate.getForEntity(String.format("/authorization/user/cb/%s", it), UserResponse.class)));

        assertEquals(successCount + internalServerErrorCount + cbOpenErrorCount, responses.size());
        assertEquals(successCount, responses.stream().filter(it -> it.getStatusCode() == HttpStatus.OK).count());
        assertEquals(internalServerErrorCount, responses.stream().filter(it -> it.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR).count());
        assertEquals(cbOpenErrorCount, responses.stream().filter(it -> it.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE).count());
        verify(userRepoService, times(successCount + internalServerErrorCount)).getById(anyLong());
    }

    private User[] returnMockSuccess(int count) {
        return IntStream.rangeClosed(1, count).boxed().map(it -> user).toArray(User[]::new);
    }

    private Throwable[] returnMockNotSuccess(int count) {
        return IntStream.rangeClosed(1, count).boxed().map(it -> new RuntimeException()).toArray(Throwable[]::new);
    }
}
