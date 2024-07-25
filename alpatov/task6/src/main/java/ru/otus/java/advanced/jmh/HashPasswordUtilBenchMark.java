package ru.otus.java.advanced.jmh;

import org.openjdk.jmh.annotations.*;
import ru.otus.java.advanced.util.HashPasswordUtil;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode({Mode.All})
public class HashPasswordUtilBenchMark {

    @Param({"SHA-256", "SHA-512", "MD-5"})
    public String algorithm;

    public String password = "qwerty1234567890";

    @Benchmark
    public void passwordHashingBenchmark() {
        switch (algorithm) {
            case "SHA-256", "MD-5", "SHA-512" -> HashPasswordUtil.hash(password, algorithm);
        }
    }

}
