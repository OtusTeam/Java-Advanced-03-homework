package otus.moryakovdv.task6.jmh;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

public class BenchmarkAverageTime {
	
	@Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)

    public void measureAvgTime() throws InterruptedException {

        TimeUnit.MILLISECONDS.sleep(100);

    }
	
	

}
