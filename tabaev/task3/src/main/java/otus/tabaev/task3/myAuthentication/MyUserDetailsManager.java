package otus.tabaev.task3.myAuthentication;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import otus.tabaev.task3.cache.MyCache;
import otus.tabaev.task3.model.MyUser;
import otus.tabaev.task3.repository.MyUserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsManager implements UserDetailsManager {

    @Autowired
    private final MyUserRepository myUserRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;


    @Override
    public void createUser(UserDetails user) {
        if (userExists(user.getUsername())) {
            throw new IllegalArgumentException("User with username: " + user.getUsername() + " already exists.");
        }

        MyUser myUser = new MyUser(user.getUsername(), passwordEncoder.encode(user.getPassword()));
        myUserRepository.save(myUser);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return myUserRepository.findByLogin(username).isPresent();
    }

    @Override
    @MyCache
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> optionalMyUser = myUserRepository.findByLogin(username);
        MyUser myUser = optionalMyUser.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new MyUserDetails(myUser);
    }
}
