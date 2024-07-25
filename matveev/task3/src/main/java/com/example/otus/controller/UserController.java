package com.example.otus.controller;

import com.example.otus.Repository.UserRepository;
import com.example.otus.cache.Cache;
import com.example.otus.models.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Cache cache;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository, Cache cache) {
        this.cache = cache;
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userRepository.findById(id).orElseThrow());
    }

    @PostMapping("")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User saveUser = userRepository.save(user);
        cache.put(user.getName(), user.getPassword());
        return ResponseEntity.ok(saveUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        User currentUser = userRepository.findById(id).orElseThrow();
        currentUser.setName(user.getName());
        currentUser.setPassword(user.getPassword());
        User updatedUser = userRepository.save(currentUser);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long id) {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }

}
