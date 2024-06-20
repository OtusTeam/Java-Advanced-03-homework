package ru.nicshal.advanced.outofmemory.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.nicshal.advanced.outofmemory.data.User;
import ru.nicshal.advanced.outofmemory.repositories.UserRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserRepositoryUserDetailsService
        implements UserDetailsService {

    private UserRepository userRepo;

    @Autowired
    public UserRepositoryUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user != null) {
            return user;
        }

        //TODO - внесенная ошибка для эмуляции OutOfMemoryError
        /*List<Object[]> arrays = new LinkedList<>();
        for (; ; ) {
            arrays.add(new Object[10]);
        }*/

        throw new UsernameNotFoundException(
                "Пользователь '" + username + "' не найден");
    }

}