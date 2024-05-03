package ru.skilanov.web;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skilanov.dto.UserDto;
import ru.skilanov.service.UserService;
import ru.skilanov.web.request.CreateUserRequest;
import ru.skilanov.web.response.UserCreateResponse;

import static org.modelmapper.convention.MatchingStrategies.STRICT;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserCreateResponse> register(@Valid @RequestBody CreateUserRequest request) {
        var mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(STRICT);
        var entity = mapper.map(request, UserDto.class);
        var result = userService.insert(entity);
        var response = mapper.map(result, UserCreateResponse.class);

        return ResponseEntity.status(CREATED).body(response);
    }
}
