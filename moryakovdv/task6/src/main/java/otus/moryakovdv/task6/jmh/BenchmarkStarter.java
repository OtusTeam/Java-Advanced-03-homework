package otus.moryakovdv.task6.jmh;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkStarter {
	
	public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
        		.jvmArgsAppend("-Djmh.ignoreLock=true")
        		
        		.include(BenchmarkAverageTime.class.getSimpleName())
                
                //.include(JMHSample_33_SecurityManager.class.getSimpleName())

        		
                .warmupIterations(5)

                .measurementIterations(5)

                .forks(1)
                .build();


        new Runner(opt).run();

    }

}
