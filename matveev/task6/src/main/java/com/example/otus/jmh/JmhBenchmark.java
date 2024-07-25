package com.example.otus.jmh;

import com.example.otus.models.User;
import com.example.otus.utils.EncryptType;
import com.example.otus.utils.Utils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@State(Scope.Thread)
public class JmhBenchmark {

    Utils utils = new Utils();
    private final int operationCount = 100;

    @Param({EncryptType.MD5, EncryptType.SHA256, EncryptType.SHA512})
    private String type;

    @Benchmark
    @BenchmarkMode(Mode.All)
    @Measurement(iterations = 3, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    @OperationsPerInvocation(operationCount)
    public void testEncrypt() {
        IntStream.range(0, operationCount)
                .mapToObj(i -> new User("user" + i, "pass" + i))
                .forEach(user -> utils.encrypt(type, user.getPassword()));
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @Measurement(iterations = 3, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    @OperationsPerInvocation(operationCount)
    public void testEncryptWithBh(Blackhole bh) {
        IntStream.range(0, operationCount)
                .mapToObj(i -> new User("user" + i, "pass" + i))
                .map(user -> utils.encrypt(type, user.getPassword())).forEach(bh::consume);
    }

}
