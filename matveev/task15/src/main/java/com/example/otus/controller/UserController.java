package com.example.otus.controller;

import com.example.otus.repository.UserRepository;
import com.example.otus.models.User;
import com.example.otus.utils.Utils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final Utils utils;
    final String[] array = new String[]{"md5", "sha256", "sha512"};


    @Autowired
    public UserController(UserRepository userRepository, Utils utils) {
        this.userRepository = userRepository;
        this.utils = utils;
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @Operation(
            summary = "Create user",
            description = "Creates user with random hash pass algs"
    )
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        Random random = new Random();
        int index = random.nextInt(array.length);
        String encryptType = array[index];
        System.out.println(encryptType);
        user.setPassword(utils.encrypt(encryptType, user.getPassword()));
        User saveUser = userRepository.save(user);
        return ResponseEntity.ok(saveUser);
    }
    @Operation(summary = "Create user md5 hash")
    @PostMapping("/md5")
    public ResponseEntity<User> createUserMd5Hex(@Valid @RequestBody User user) {
        user.setPassword(utils.md5Encrypt(user.getPassword()));
        User saveUser = userRepository.save(user);
        return ResponseEntity.ok(saveUser);
    }

    @Operation(summary = "Create user sha256 hash")
    @PostMapping("/sha256")
    public ResponseEntity<User> createUserSha256(@Valid @RequestBody User user) {
        user.setPassword(utils.sha256Encrypt(user.getPassword()));
        User saveUser = userRepository.save(user);
        return ResponseEntity.ok(saveUser);
    }

    @Operation(summary = "Create user sha512 hash")
    @PostMapping("/sha512")
    public ResponseEntity<User> createUserSha512(@Valid @RequestBody User user) {
        user.setPassword(utils.sha512Encrypt((user.getPassword())));
        User saveUser = userRepository.save(user);
        return ResponseEntity.ok(saveUser);
    }

}
