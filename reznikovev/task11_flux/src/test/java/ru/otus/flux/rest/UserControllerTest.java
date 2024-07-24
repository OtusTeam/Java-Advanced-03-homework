package ru.otus.flux.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;
import ru.otus.flux.model.User;
import ru.otus.flux.model.UserMapper;
import ru.otus.flux.repo.UserRepository;
import ru.otus.flux.service.UserService;

import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = UserController.class)
@Import(UserService.class)
class UserControllerTest {

    @MockBean
    UserRepository repository;

    @MockBean(name = "userCache")
    Cache cache;

    @MockBean
    UserMapper userMapper;

    @Autowired
    private WebTestClient webClient;

    @Test
    void testCreateUser() {
        var mapper = Mappers.getMapper(UserMapper.class);

        var user = new User();
        user.setId(1L);
        user.setLogin("login_1");
        user.setPassword("pass_1");

        Mockito.when(repository.save(user)).thenReturn(Mono.just(user));
        Mockito.when(userMapper.toDto(user)).thenReturn(mapper.toDto(user));

        webClient.post()
                .uri("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(user))
                .exchange()
                .expectStatus().isOk();

        Mockito.verify(repository, times(1)).save(user);
    }


    @Test
    void testGetUserById() {
        var mapper = Mappers.getMapper(UserMapper.class);

        var user = new User();
        user.setId(1L);
        user.setLogin("login_1");
        user.setPassword("pass_1");

        Mockito.when(userMapper.toDto(user)).thenReturn(mapper.toDto(user));
        Mockito.when(cache.get(1L))
                .thenReturn(new SimpleValueWrapper(user));

        webClient.get()
                .uri("/api/user/{id}", 1L)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.login").isNotEmpty()
                .jsonPath("$.password").isNotEmpty()
                .jsonPath("$.id").isEqualTo(1L)
                .jsonPath("$.login").isEqualTo("login_1")
                .jsonPath("$.password").isEqualTo("pass_1");

        Mockito.verify(cache, times(1)).get(1L);
        Mockito.verify(repository, times(0)).findById(1L);
    }

    @Test
    void testDeleteUser() {
        Mono<Void> voidReturn = Mono.empty();
        var user = new User();
        user.setId(1L);
        user.setLogin("login_1");
        user.setPassword("pass_1");

        Mockito.when(cache.evictIfPresent(1L))
                .thenReturn(true);
        Mockito.when(repository.findById(1L))
                .thenReturn(Mono.just(user));
        Mockito.when(repository.deleteById(1L))
                .thenReturn(voidReturn);

        webClient.delete()
                .uri("/api/user/{id}", 1)
                .exchange()
                .expectStatus().isOk();

        Mockito.verify(cache, times(1)).evictIfPresent(1L);
        Mockito.verify(repository, times(1)).deleteById(1L);
    }

}