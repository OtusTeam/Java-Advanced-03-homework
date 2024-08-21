package ru.otus.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.metrics.UsersCounter;
import ru.otus.model.User;
import ru.otus.model.UserData;
import ru.otus.repository.UserRepository;
import ru.otus.service.UserServiceImpl;
import ru.otus.service.encryptor.UserEncryptor;
import ru.otus.service.usermonitoring.UserMonitoringService;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = UserController.class)
@Import(UserServiceImpl.class)
public class UserControllerTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserMonitoringService userMonitoringService;

    @MockBean
    private UserEncryptor userEncryptor;

    @MockBean
    private UsersCounter usersCounter;

    @Autowired
    private WebTestClient webClient;

    @Test
    public void getUsers() {
        String userLogin = "vasya";
        User user = new User(userLogin, "#@##%%");

        Mockito
                .when(userRepository.findAll())
                .thenReturn(Flux.just(user));

        webClient.get()
                .uri("/users/")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(String.class).contains(userLogin);
    }

    @Test
    public void getUser() {
        String userLogin = "vasya";
        User user = new User(userLogin, "#$@##%");

        Mockito
                .when(userRepository.findById(userLogin))
                .thenReturn(Mono.just(user));

        webClient.get()
                .uri("/users/{login}", userLogin)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class).isEqualTo(userLogin);
    }

    @Test
    public void saveUser() {
        String userLogin = "vasya";
        User user = new User(userLogin, "&^&#^$*&");

        Mockito
                .when(userEncryptor.encrypt(Mockito.any(User.class)))
                .thenReturn(user);

        Mockito
                .when(userRepository.save(user))
                .thenReturn(Mono.just(user));

        webClient.post()
                .uri("/users")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(user)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo(userLogin);
    }

    @Test
    public void updateUser() {
        String userLogin = "vasya";
        User user = new User(userLogin, "&(&&W#%");

        Mockito
                .when(userEncryptor.encrypt(Mockito.any(User.class)))
                .thenReturn(user);

        Mockito
                .when(userRepository.save(user))
                .thenReturn(Mono.just(user));

        webClient.put()
                .uri("/users")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(user)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo(userLogin);
    }

    @Test
    public void deleteUser() {
        String userLogin = "vasya";

        Mockito
                .when(userRepository.deleteById(userLogin))
                .thenReturn(Mono.empty());

        webClient.delete()
                .uri("/users/{login}", userLogin)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo(userLogin);
    }

    @Test
    public void getUserReport() {
        UserData userData = new UserData("0000000", "vasya", "1970-01-01 00:00:00.000");

        Mockito
                .when(userMonitoringService.getUserReport())
                .thenReturn(Flux.just(userData));

        webClient.get()
                .uri("/users/report")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(UserData.class).contains(userData);
    }
}
