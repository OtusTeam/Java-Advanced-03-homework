package com.homework.otus.controller;

import com.homework.otus.repository.UserRepository;
import com.homework.otus.models.User;
import com.homework.otus.utils.Utils;
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
    final String[] array = new String[]{"md5", "sha256", "sha512", "notExist"};


    @Autowired
    public UserController(UserRepository userRepository, Utils utils) {
        this.userRepository = userRepository;
        this.utils = utils;
    }

    @GetMapping("")
    public synchronized ResponseEntity<List<User>> getAllUsers() {
        List<User> all = userRepository.findAll();
        all.forEach(user -> user.setId(userRepository.getReferenceById(user.getId()).getId() + 1));
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/exception")
    public ResponseEntity<List<User>> getException() {
        utils.encrypt("notExist","12345");
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        Random random = new Random();
        int index = random.nextInt(array.length);
        String encryptType = array[index];
        System.out.println(encryptType);
        if (user.getPassword().length() > 5) {
            System.out.println("Password is too strong, make it easier");
            throw new IllegalArgumentException("Password is too strong, make it easier");
        }
        user.setPassword(utils.encrypt(encryptType, user.getPassword()));
        User saveUser = userRepository.save(user);
        return ResponseEntity.ok(saveUser);
    }


}
