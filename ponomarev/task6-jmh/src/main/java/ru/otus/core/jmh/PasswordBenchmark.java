package ru.otus.core.jmh;

import org.openjdk.jmh.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.core.service.HashPasswordService;

import java.util.concurrent.TimeUnit;

/**
 * @author Anton Ponomarev on 22.07.2024
 * @project Java-Advanced-homework
 */
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Component
public class PasswordBenchmark {

    @Autowired
    private HashPasswordService hashPasswordService;

    @Param({"MD5", "SHA-256", "SHA-512"})
    public String algorithm;

    public String password = "testPassword";

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void testPasswordHashingThroughputMode() {
        if (algorithm.matches("MD5|SHA-256|SHA-512")) {
            hashPasswordService.hashPassword(password, algorithm);
        }
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void testPasswordHashingAverageTimeMode() {
        if (algorithm.matches("MD5|SHA-256|SHA-512")) {
            hashPasswordService.hashPassword(password, algorithm);
        }
    }
    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    public void testPasswordHashingSampleTimeMode() {
        if (algorithm.matches("MD5|SHA-256|SHA-512")) {
            hashPasswordService.hashPassword(password, algorithm);
        }
    }
    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public void testPasswordHashingSingleShotTimeMode() {
        if (algorithm.matches("MD5|SHA-256|SHA-512")) {
            hashPasswordService.hashPassword(password, algorithm);
        }
    }
}
