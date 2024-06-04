package ru.skilanov.jmh;


import org.openjdk.jmh.annotations.*;
import ru.skilanov.utils.PasswordHashingUtil;

import java.util.concurrent.TimeUnit;

@BenchmarkMode({Mode.Throughput, Mode.AverageTime, Mode.SampleTime, Mode.SingleShotTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class PasswordHashingBenchmark {

    @Param({"MD5", "SHA-256", "SHA-512"})
    public String algorithm;

    public String password = "testPassword";

    @Benchmark
    public void testPasswordHashing() {
        if (algorithm.matches("MD5|SHA-256|SHA-512")) {
            PasswordHashingUtil.hashPassword(password, algorithm);
        }
    }
}