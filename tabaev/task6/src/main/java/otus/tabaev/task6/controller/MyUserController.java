package otus.tabaev.task6.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import otus.tabaev.task6.controller.dto.CreateUserRequest;
import otus.tabaev.task6.service.MyUserService;

@RestController
@RequestMapping("/user")
public class MyUserController {

    private final MyUserService userService;

    public MyUserController(MyUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<Void> registration(@RequestBody CreateUserRequest request) {
        userService.createUser(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }


}
