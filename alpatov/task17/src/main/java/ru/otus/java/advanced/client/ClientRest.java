package ru.otus.java.advanced.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.java.advanced.service.UserService;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ClientRest {

    private final UserService userService;

    public Integer callApi(UUID id) {
        return userService.getAge(id);
    }
}
