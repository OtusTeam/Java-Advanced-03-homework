package ru.otus.jmh;

import org.junit.runner.RunWith;
import org.openjdk.jmh.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.service.UserService;
import ru.otus.model.User;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@State(Scope.Benchmark)
@RunWith(SpringRunner.class)
@BenchmarkMode({Mode.All})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class UserServiceBenchmark extends AbstractBenchmark {

    private final User TEST_USER = new User("vasya", "123456");

    private static UserService userService;

    @Autowired
    public void setUserDao(UserService userService) {
        UserServiceBenchmark.userService = userService;
    }

    @TearDown(Level.Invocation)
    public void deleteUser() {
        userService.delete(TEST_USER.getId());
    }

    @Benchmark
    public void saveUser() {
        userService.save(TEST_USER);
    }
}
