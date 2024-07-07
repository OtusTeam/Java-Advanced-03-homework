package otus.tabaev.task11.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import otus.tabaev.task11.controller.dto.CreateUserRequest;
import otus.tabaev.task11.controller.dto.CreateUserResponse;
import otus.tabaev.task11.controller.dto.LoginUserRequest;
import otus.tabaev.task11.controller.dto.LoginUserResponse;
import otus.tabaev.task11.controller.dto.UsersFindAllUsersResponse;
import otus.tabaev.task11.service.MyUserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class MyUserController {

    private final MyUserService userService;

    public MyUserController(MyUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public Mono<CreateUserResponse> registration(@RequestBody CreateUserRequest request) {
        return userService.createUser(request)
                .map(myUser -> new CreateUserResponse(myUser.getId(), myUser.getLogin(), true));
    }

    @PostMapping("/login")
    public Mono<LoginUserResponse> login(@RequestBody LoginUserRequest request) {
        return userService.loginUser(request)
                .map(user -> new LoginUserResponse(user.getLogin(), true));
    }

    @GetMapping
    public Flux<UsersFindAllUsersResponse> findAllUsers() {
        return userService.findAllUsers()
                .map(user -> new UsersFindAllUsersResponse(user.getId(), user.getLogin()));
    }

}
