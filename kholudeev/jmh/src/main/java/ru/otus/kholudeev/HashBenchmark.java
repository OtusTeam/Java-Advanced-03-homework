package ru.otus.kholudeev;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import ru.otus.kholudeev.service.PasswordHashingService;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static ru.otus.kholudeev.constant.EncryptAlgorithm.*;

@State(Scope.Thread)
public class HashBenchmark {

    private final PasswordHashingService passwordHashingService = new PasswordHashingService();
    private final int operationCount = 100;

    @Param({MD5, SHA256, SHA512})
    private String algorithm;

    @Benchmark
    @BenchmarkMode(Mode.All)
    @Fork(value = 3, warmups = 0)
    @Warmup(iterations = 1)
    @Measurement(iterations = 3, time = 20, timeUnit = TimeUnit.MILLISECONDS)
    @OperationsPerInvocation(operationCount)
    public void testHashPassword(Blackhole bh) {
        for (int i = 0; i < operationCount; i++) {
            bh.consume(passwordHashingService.getHashedPassword(UUID.randomUUID().toString(), algorithm));
        }
    }
}
