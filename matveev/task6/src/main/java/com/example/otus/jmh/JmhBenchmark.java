package com.example.otus.jmh;

import com.example.otus.models.User;
import com.example.otus.utils.Utils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
public class JmhBenchmark {

    Utils utils = new Utils();

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 1, warmups = 0)
    @Warmup(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 7, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    @OperationsPerInvocation(10000)
    public void testmd5() {
        for (int i = 0; i < 10000; i++) {
            User user = new User("user" + i, "pass" + i);
            utils.md5Encrypt(user.getPassword());
        }
    }


    @Benchmark
    @OperationsPerInvocation(10000)
    @BenchmarkMode(Mode.All)
    @Warmup(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 7, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    public void testSha256() {
        for (int i = 0; i < 10000; i++) {
            User user = new User("user" + i, "pass" + i);
            utils.sha256Encrypt(user.getPassword());
        }
    }

    @Benchmark
    @OperationsPerInvocation(10000)
    @BenchmarkMode(Mode.All)
    @Warmup(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 7, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    public void testSha512() {
        for (int i = 0; i < 10000; i++) {
            User user = new User("user" + i, "pass" + i);
            utils.sha512Encrypt(user.getPassword());
        }
    }

    @Benchmark
    @OperationsPerInvocation(10000)
    @BenchmarkMode(Mode.All)
    @Threads(10)
    @Warmup(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 7, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    public void testmd5Bh(Blackhole bh) {
        for (int i = 0; i < 10000; i++) {
            User user = new User("user" + i, "pass" + i);
            bh.consume(utils.md5Encrypt(user.getPassword()));
        }
    }

    @Benchmark
    @OperationsPerInvocation(10000)
    @BenchmarkMode(Mode.All)
    @Warmup(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 7, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    public void testSha256Bh(Blackhole bh) {
        for (int i = 0; i < 10000; i++) {
            User user = new User("user" + i, "pass" + i);
            bh.consume(utils.sha256Encrypt(user.getPassword()));
        }
    }

    @Benchmark
    @OperationsPerInvocation(10000)
    @BenchmarkMode(Mode.All)
    @Warmup(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 7, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    public void testSha512Bh(Blackhole bh) {
        for (int i = 0; i < 10000; i++) {
            User user = new User("user" + i, "pass" + i);
            bh.consume(utils.sha512Encrypt(user.getPassword()));
        }
    }

}
