package otus.tabaev.task11.myAuthentication;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import otus.tabaev.task11.model.MyUser;
import otus.tabaev.task11.repository.MyUserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Service
@RequiredArgsConstructor
public class MyUserDetailsManager implements ReactiveUserDetailsService {

    @Autowired
    private final MyUserRepository myUserRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;


    public Mono<MyUser> createUser(UserDetails user) {
        return userExists(user.getUsername())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new IllegalArgumentException("User with username: " + user.getUsername() + " already exists."));
                    }
                    MyUser myUser = new MyUser(user.getUsername(), passwordEncoder.encode(user.getPassword()));
                    return myUserRepository.save(myUser);
                });
    }


    public Mono<MyUser> deleteUser(String username) {
        return myUserRepository.deleteByLogin(username);
    }

    public Mono<MyUser> loginUser(String username, String password) {
        return myUserRepository.findByLogin(username)
                .switchIfEmpty(Mono.error(new BadCredentialsException("User not found with username: " + username)))
                .flatMap(user -> {
                    if (passwordEncoder.matches(password, user.getPassword())) {
                        return Mono.just(user);
                    } else {
                        return Mono.error(new BadCredentialsException("Invalid password for user with username: " + username));
                    }
                });
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return myUserRepository.findByLogin(username)
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found with username: " + username)))
                .map(myUser -> new MyUserDetails(myUser));
    }

    public Flux<MyUser> findAllUsers() {
        return myUserRepository.findAll();
    }


    public Mono<Boolean> userExists(String username) {
        return myUserRepository.findByLogin(username)
                .map(user -> true)
                .defaultIfEmpty(false);
    }
}
