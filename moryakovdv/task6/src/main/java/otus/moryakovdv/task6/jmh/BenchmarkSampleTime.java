package otus.moryakovdv.task6.jmh;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

@BenchmarkMode(Mode.SampleTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
/**Замеры времени выполнения*/
public class BenchmarkSampleTime extends ParentBenchmark {

	
}
