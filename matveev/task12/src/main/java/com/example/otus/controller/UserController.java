package com.example.otus.controller;

import com.example.otus.repository.UserRepository;
import com.example.otus.models.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @GetMapping("")
    public synchronized ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        user.setPassword(user.getPassword());
        User saveUser = userRepository.save(user);
        System.out.println("User was created: " + user.getName());
        return ResponseEntity.ok(saveUser);
    }


}
