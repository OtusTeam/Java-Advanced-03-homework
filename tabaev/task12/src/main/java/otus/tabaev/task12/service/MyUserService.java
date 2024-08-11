package otus.tabaev.task12.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.tabaev.task12.controller.dto.CreateUserRequest;
import otus.tabaev.task12.controller.dto.LoginUserRequest;
import otus.tabaev.task12.model.MyUser;
import otus.tabaev.task12.myAuthentication.MyUserDetailsManager;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class MyUserService {

    private final MyUserDetailsManager userDetailsManager;

    public Mono<MyUser> createUser(CreateUserRequest request) {

        UserDetails userDetails = User.builder()
                .username(request.getLogin())
                .password(request.getPassword())
                .build();

        return userDetailsManager.createUser(userDetails);

    }

    public Mono<MyUser> loginUser(LoginUserRequest request) {
        return userDetailsManager.loginUser(request.getLogin(), request.getPassword());
    }

    public Flux<MyUser> findAllUsers() {
        return userDetailsManager.findAllUsers();
    }


    @Transactional
    public void delelteuserbylogin(String login) {
        userDetailsManager.deleteUser(login);
    }
}
