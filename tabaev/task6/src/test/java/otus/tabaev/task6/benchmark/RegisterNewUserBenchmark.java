package otus.tabaev.task6.benchmark;

import org.openjdk.jmh.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import otus.tabaev.task6.benchmark.config.AbstractBenchmark;
import otus.tabaev.task6.controller.dto.CreateUserRequest;
import otus.tabaev.task6.service.MyUserService;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@State(Scope.Benchmark)
@BenchmarkMode({Mode.AverageTime, Mode.Throughput, Mode.SampleTime, Mode.SingleShotTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class RegisterNewUserBenchmark extends AbstractBenchmark {

    private final String usernameExample = "Abob@17032001";
    private final String passwordExample = "Abob@17032001";


    private static MyUserService userService;

    @Autowired
    public void setUserService(MyUserService userService) {
        RegisterNewUserBenchmark.userService = userService;
    }

    @TearDown(Level.Invocation)
    public void cleanup() {
        userService.delelteuserbylogin(usernameExample);
    }

    @Benchmark
    public void benchmarkRegisterNewUser() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setLogin(usernameExample);
        createUserRequest.setPassword(passwordExample);

        userService.createUser(createUserRequest);
    }

}
