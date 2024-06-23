package com.example.otus.controller;

import com.example.otus.Repository.UserRepository;
import com.example.otus.models.User;
import com.example.otus.utils.Utils;
import jakarta.validation.Valid;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final Utils utils;

    @Autowired
    public UserController(UserRepository userRepository, Utils utils) {
        this.userRepository = userRepository;
        this.utils = utils;
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping("/md5")
    public ResponseEntity<User> createUserMd5Hex(@Valid @RequestBody User user) {
        user.setPassword(utils.md5Encrypt(user.getPassword()));
        User saveUser = userRepository.save(user);
        return ResponseEntity.ok(saveUser);
    }

    @PostMapping("/sha256")
    public ResponseEntity<User> createUserSha256(@Valid @RequestBody User user) {
        user.setPassword(utils.sha256Encrypt(user.getPassword()));
        User saveUser = userRepository.save(user);
        return ResponseEntity.ok(saveUser);
    }

    @PostMapping("/sha512")
    public ResponseEntity<User> createUserSha512(@Valid @RequestBody User user) {
        user.setPassword(utils.sha512Encrypt((user.getPassword())));
        User saveUser = userRepository.save(user);
        return ResponseEntity.ok(saveUser);
    }

}
