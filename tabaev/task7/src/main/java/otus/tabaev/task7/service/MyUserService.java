package otus.tabaev.task7.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import otus.tabaev.task7.controller.dto.CreateUserRequest;
import otus.tabaev.task7.myAuthentication.MyUserDetailsManager;


@Service
@RequiredArgsConstructor
public class MyUserService {

    private final MyUserDetailsManager userDetailsManager;

    public UserDetails createUser(CreateUserRequest request) {

        UserDetails userDetails = User.builder()
                .username(request.getLogin())
                .password(request.getPassword())
                .build();

        userDetailsManager.createUser(userDetails);

        return userDetails;

    }

    @Transactional
    public void delelteuserbylogin(String login) {
        userDetailsManager.deleteUser(login);
    }
}
