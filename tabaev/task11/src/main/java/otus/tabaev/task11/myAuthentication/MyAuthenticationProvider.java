package otus.tabaev.task11.myAuthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MyAuthenticationProvider implements ReactiveAuthenticationManager {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ReactiveUserDetailsService reactiveUserDetailsService;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        return reactiveUserDetailsService.findByUsername(username)
                .switchIfEmpty(Mono.error(new BadCredentialsException("No user registered with these credentials!")))
                .flatMap(userDetails -> {
                    if (passwordEncoder.matches(password, userDetails.getPassword())) {
                        return Mono.just(new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities()));
                    } else {
                        return Mono.error(new BadCredentialsException("Invalid password!"));
                    }
                });
    }
}
