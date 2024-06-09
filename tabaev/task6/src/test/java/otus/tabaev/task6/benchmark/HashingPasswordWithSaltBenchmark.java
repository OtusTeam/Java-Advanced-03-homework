package otus.tabaev.task6.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.springframework.boot.test.context.SpringBootTest;
import otus.tabaev.task6.benchmark.config.AbstractBenchmark;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@State(Scope.Benchmark)
@BenchmarkMode({Mode.AverageTime, Mode.Throughput, Mode.SampleTime, Mode.SingleShotTime})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class HashingPasswordWithSaltBenchmark extends AbstractBenchmark {

    private final String passwordExample = "Abob@17032001";

    private MessageDigest md5Digest;
    private MessageDigest sha256Digest;
    private MessageDigest sha512Digest;


    @Setup(Level.Trial)
    public void setUp() throws NoSuchAlgorithmException {
        md5Digest = MessageDigest.getInstance("MD5");
        sha256Digest = MessageDigest.getInstance("SHA-256");
        sha512Digest = MessageDigest.getInstance("SHA-512");
    }

    @Benchmark
    public void md5HashingPasswordWithSaltBenchmark(Blackhole blackhole) {
        blackhole.consume(md5Digest.digest(passwordExample.getBytes()));
    }

    @Benchmark
    public void sha256HashingPasswordWithSaltBenchmark(Blackhole blackhole) {
        blackhole.consume(sha256Digest.digest(passwordExample.getBytes()));
    }

    @Benchmark
    public void sha512HashingPasswordWitSaltBenchmark(Blackhole blackhole) {
        blackhole.consume(sha512Digest.digest(passwordExample.getBytes()));
    }

}
