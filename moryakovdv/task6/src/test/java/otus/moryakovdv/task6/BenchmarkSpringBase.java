package otus.moryakovdv.task6;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**Интеграция JUNIT+Springboot+JMH
 * 
 * https://gist.github.com/msievers/ce80d343fc15c44bea6cbb741dde7e45
 * */
public class BenchmarkSpringBase {
	

    @Test
    public void executeJmhRunner() throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(UserServiceJmhBenchmark.class.getSimpleName())
            .warmupIterations(1)
            .measurementIterations(3)
            
            // do not use forking or the benchmark methods will not see references stored within its class
            .forks(0)
            // do not use multiple threads
            .threads(1)          
            .shouldFailOnError(true)
      
            .jvmArgs("-server")
            .build();

        new Runner(opt).run();
    }
}
