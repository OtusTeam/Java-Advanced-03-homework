package ru.otus.jmh;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;


public class AbstractBenchmark {

    private final static Integer FORK_ITERATIONS = 0;
    private final static Integer MEASUREMENT_ITERATIONS = 3;
    private final static Integer WARMUP_ITERATIONS = 3;

    @Test
    public void executeJmhRunner() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include("\\." + this.getClass().getSimpleName() + "\\.")
                .warmupIterations(WARMUP_ITERATIONS)
                .measurementIterations(MEASUREMENT_ITERATIONS)
                .forks(FORK_ITERATIONS)
                .threads(1)
                .shouldDoGC(true)
                .shouldFailOnError(true)
                .resultFormat(ResultFormatType.JSON)
                .result("./encrypting-benchmarking/jmh-result.json")
                .shouldFailOnError(true)
                .jvmArgs("-server")
                .build();

        new Runner(opt).run();
    }
}
