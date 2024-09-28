package ru.otus.kholudeev;

import org.junit.jupiter.api.*;
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
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RateLimiterTest {

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
    @MockBean
    private UserRepoService userRepoService;

    /**
     * Т.к. в классе RateLimiter нет аналога reset из CurcuitBreaker для сброса метрик между тестами,
     * то есть необходимость в таком обходном пути, чтобы тесты не аффектили друг на друга
     * <a href="https://github.com/resilience4j/resilience4j/issues/1105">...</a>
     */
    @BeforeEach
    void beforeEach() {
        Resilence4j.getRegistry().remove("rpsRateLimiter");
        Resilence4j.getRegistry().remove("rpmRateLimiter");
        Resilence4j.registerRateLimiter();
    }

    @Test
    void testNotLimit() {
        var countRequest = 20;
        Mockito.when(userRepoService.getById(anyLong())).thenReturn(user);
        IntStream.rangeClosed(1, countRequest).parallel().forEach(it -> {
            var response = testRestTemplate.getForEntity(String.format("/authorization/user/%s", it), UserResponse.class);
            assertEquals(HttpStatus.OK, response.getStatusCode());
        });
        verify(userRepoService, times(countRequest)).getById(anyLong());
    }

    @Test
    void testRpsLimit() {
        var limit = 20;
        var countRequest = limit + 5;

        Mockito.when(userRepoService.getById(anyLong())).thenReturn(user);
        var responses = new CopyOnWriteArrayList<ResponseEntity<UserResponse>>();
        IntStream.rangeClosed(1, countRequest).parallel().forEach(it -> responses.add(
                testRestTemplate.getForEntity(String.format("/authorization/user/%s", it), UserResponse.class)));
        assertEquals(countRequest, responses.size());
        assertEquals(limit, responses.stream().filter(it -> it.getStatusCode() == HttpStatus.OK).count());
        assertEquals(limit, countRequest - responses.stream().filter(it -> it.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS).count());
        verify(userRepoService, times(limit)).getById(anyLong());
    }

    @Test
    void testRpmLimit() {
        var limitRPS = 20;
        var limitRPM = 30;
        var iters = 2;

        Mockito.when(userRepoService.getById(anyLong())).thenReturn(user);
        var responses = new CopyOnWriteArrayList<ResponseEntity<UserResponse>>();
        for (int i = 0; i < iters; i++) {
            IntStream.rangeClosed(1, limitRPS).forEach(it -> {
                responses.add(testRestTemplate.getForEntity(String.format("/authorization/user/%s", it), UserResponse.class));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        assertEquals(limitRPS * iters, responses.size());
        assertEquals(limitRPM, responses.stream().filter(it -> it.getStatusCode() == HttpStatus.OK).count());
        assertEquals(limitRPS * iters - limitRPM, responses.stream().filter(it -> it.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS).count());
        verify(userRepoService, times(limitRPM)).getById(anyLong());
    }
}
