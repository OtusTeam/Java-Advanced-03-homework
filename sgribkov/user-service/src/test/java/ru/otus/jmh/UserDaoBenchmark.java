package ru.otus.jmh;

import org.junit.runner.RunWith;
import org.openjdk.jmh.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.dao.UserDao;
import ru.otus.model.User;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@State(Scope.Benchmark)
@RunWith(SpringRunner.class)
@BenchmarkMode({Mode.All})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class UserDaoBenchmark extends AbstractBenchmark {

    private final User TEST_USER = new User("vasya", "123456");

    private static UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        UserDaoBenchmark.userDao = userDao;
    }

    @TearDown(Level.Invocation)
    public void deleteUser() {
        userDao.delete(TEST_USER.getId());
    }

    @Benchmark
    public void saveUser() {
        userDao.save(TEST_USER);
    }
}
