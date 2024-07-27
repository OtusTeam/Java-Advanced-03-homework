package ru.otus.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.dao.UserDao;
import ru.otus.model.User;
import ru.otus.model.UserData;
import ru.otus.service.UserMonitoringService;
import java.util.List;


@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserDao userDao;
    private final UserMonitoringService userMonitoringService;

    @GetMapping("/{login}")
    public ResponseEntity<String> getUser(@PathVariable String login) {
        User user = userDao.findByLogin(login);
        return ResponseEntity.ok(user.getId());
    }

    @GetMapping("/report")
    public ResponseEntity<List<UserData>> getUserIdentity() {
        List<UserData> reportData = userMonitoringService.getUserReport();
        return ResponseEntity.ok(reportData);
    }

    @PostMapping
    public ResponseEntity<String> saveUser(Model model,
                                           @RequestBody User user) {
        User savedUser = userDao.save(user);
        userMonitoringService.run(savedUser);
        return ResponseEntity.ok(savedUser.getId());
    }

    @PutMapping
    public ResponseEntity<String> updateUser(Model model,
                                             @RequestBody User user) {
        User updatedUser = userDao.update(user);
        return ResponseEntity.ok(updatedUser.getId());
    }

    @DeleteMapping("/{login}")
    public ResponseEntity<String> deleteUser(@PathVariable String login) {
        String deletedUserLogin = userDao.delete(login);
        boolean userMonitoringResult = userMonitoringService.stop(deletedUserLogin);

        if (!userMonitoringResult) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(deletedUserLogin);
        }
    }
}
