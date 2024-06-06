package otus.moryakovdv.task6.jmh;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import otus.moryakovdv.task6.jmh.oracle.JMHSample_21_ConsumeCPU;
import otus.moryakovdv.task6.jmh.oracle.JMHSample_27_Params;
import otus.moryakovdv.task6.jmh.oracle.JMHSample_33_SecurityManager;

/**
 * Запускающий тесты класс. Принимает в main количество warmups, forks,
 * iterations, используемых в JMH Runner
 **/
public class BenchmarkStarter {

	public static void main(String[] args) throws RunnerException {

		System.out.println("USAGE: ./startup.sh <warmups> <forks> <iterations>");

		int warmups = 1;
		int iterations = 5;
		int forks = 1;

		if (args.length < 3) {

			System.out.println("No sufficient start parameters specified. Fallback to defaults");

		} else {
			warmups = Integer.valueOf(args[0]);
			forks = Integer.valueOf(args[1]);
			iterations = Integer.valueOf(args[2]);
		}

		System.out.println(String.format("Using %d warmup, %d fork and %d iterations", warmups, forks, iterations));

		Options opt = new OptionsBuilder().jvmArgsAppend("-Djmh.ignoreLock=true")

				// классы тестов для метрик алгоритмов хеширования
				.include(BenchmarkAverageTime.class.getSimpleName())
				//.include(BenchmarkSampleTime.class.getSimpleName())
				//.include(BenchmarkSingleShotTime.class.getSimpleName())
				//.include(BenchmarkThroughput.class.getSimpleName())

				// классы тестов из примеров Oracle
				//.include(JMHSample_21_ConsumeCPU.class.getSimpleName())
				//.include(JMHSample_27_Params.class.getSimpleName())
				//.include(JMHSample_33_SecurityManager.class.getSimpleName())

				.warmupIterations(warmups)

				.measurementIterations(iterations)

				.forks(forks).build();

		new Runner(opt).run();

	}

}
