package ru.otus.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.model.User;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.concurrent.TimeUnit;
import org.junit.runner.RunWith;
import ru.otus.service.encryptor.UserEncryptor;
import ru.otus.service.encryptor.UserEncryptorImpl;

@SpringBootTest
@State(Scope.Benchmark)
@RunWith(SpringRunner.class)
@BenchmarkMode({Mode.All})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class UserEncryptorBenchmark extends AbstractBenchmark {

    private final User TEST_USER = new User("vasya", "123456");

    private UserEncryptor userEncryptor;

    @State(Scope.Benchmark)
    public static class ExecutionPlan {

        @Param({"UTF-8"})
        public String charset;

        @Param({"MD5", "SHA-256", "SHA-512"})
        public String algorithm;

        @Param({"false", "true"})
        public boolean isSalted;
    }

    @Setup(Level.Invocation)
    public void prepare(ExecutionPlan plan){
        userEncryptor = new UserEncryptorImpl(
                plan.charset,
                plan.algorithm,
                plan.isSalted);
    }

    @Benchmark
    public void encrypt(Blackhole bh) {
        bh.consume(userEncryptor.encrypt(TEST_USER));
    }
}
