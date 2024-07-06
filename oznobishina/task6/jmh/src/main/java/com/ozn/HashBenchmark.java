package com.ozn;

import org.example.service.HashService;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import org.openjdk.jmh.runner.options.WarmupMode;

public class HashBenchmark {
    private static final String PASSWORD = "super save password";

    /**
     * Simple example
     */

//    @Benchmark
//    @BenchmarkMode(Mode.Throughput)
//    @Fork(value = 1, warmups = 0)
//    @Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
//    @Measurement(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
//    public void hashShort(Blackhole bh) {
//        bh.consume(HashService.hash256(PASSWORD));
//    }

    /**
     * JMHSample_27_Params
     */
//
//    @State(Scope.Benchmark)
//    public static class ExecutionPlan {
//        @Param({"1", "10", "100"})
//        public int n;
//
//        @Setup(Level.Trial)
//        public void setUpTrial() {
//            System.out.println("trial before " + n);
//        }
//    }
//
//    @Benchmark
//    public void hashShortWithParams(Blackhole bh, ExecutionPlan plan) {
//        bh.consume(HashService.hash512(PASSWORD,  plan.n));
//    }
//
//    public static void main(String[] args) throws Exception {
//        Options opt = new OptionsBuilder()
//                .include(HashBenchmark.class.getSimpleName())
//                .mode(Mode.All)
//                .warmupTime(TimeValue.seconds(1))
//                .warmupIterations(1)
//                .forks(1)
//                .warmupForks(0)
//                .measurementIterations(1)
//                .measurementTime(TimeValue.seconds(1))
//                .build();
//
//        new Runner(opt).run();
//    }


    /**
     * JMHSample_17_SyncIterations
     */
//    @Benchmark
//    public void hash512(Blackhole bh) {
//        bh.consume(org.example.service.HashService.hash512(PASSWORD, 1));
//    }
//
//    @Benchmark
//    public void hash256(Blackhole bh) {
//        bh.consume(org.example.service.HashService.hash256(PASSWORD));
//    }
//
//    @Benchmark
//    public void hashMD5(Blackhole bh) {
//        bh.consume(org.example.service.HashService.hashMD5(PASSWORD));
//    }

//    public static void main(String[] args) throws Exception {
//        Options opt = new OptionsBuilder()
//                .include(HashBenchmark.class.getSimpleName())
//                .mode(Mode.All)
//                .warmupTime(TimeValue.seconds(1))
//                .warmupIterations(1)
//                .forks(1)
//                .warmupForks(0)
//                .measurementIterations(1)
//                .measurementTime(TimeValue.seconds(1))
//                .threads(Runtime.getRuntime().availableProcessors() * 16)
//                .syncIterations(true) // try to switch to "false"
//                .build();
//
//        new Runner(opt).run();
//    }

    /**
     * JMHSample_32_BulkWarmup
     */

    @Benchmark
    public void hashShort_1(Blackhole bh) {
        bh.consume(HashService.hash512(PASSWORD, 5));
    }

    @Benchmark
    public void hashShort_2(Blackhole bh) {
        bh.consume(HashService.hash512(PASSWORD, 50));
    }

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(HashBenchmark.class.getSimpleName())
                .mode(Mode.All)
                .warmupTime(TimeValue.seconds(1))
                .warmupIterations(1)
                .warmupMode(WarmupMode.BULK)
                .forks(1)
                .warmupForks(0)
                .measurementIterations(1)
                .measurementTime(TimeValue.seconds(1))
                .build();

        new Runner(opt).run();
    }
}
