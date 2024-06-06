package otus.moryakovdv.task6;

import static org.junit.Assert.assertNotNull;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import org.junit.runner.RunWith;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import otus.moryakovdv.task6.model.User;
import otus.moryakovdv.task6.web.UserController;

@SpringBootTest
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@RunWith(SpringRunner.class)
/**
 * Класс имплементирующий загрузку SpringApplicationContext и JMH-тестирование
 * login-сервиса
 * https://gist.github.com/msievers/ce80d343fc15c44bea6cbb741dde7e45
 */
public class UserServiceJmhBenchmark extends BenchmarkSpringBase {

	/**
	 * The most important thing is to get Spring (autowired) components into the
	 * executing benchmark instance. To accomplish this you can do the following
	 * 
	 * * set `forks` to 0 for the JMH runner to run the benchmarks within the same
	 * VM * store all @Autowired dependencies into static fields of the surrounding
	 * class
	 * 
	 */
	private static ApplicationContext cntx;

	/**
	 * We use setter autowiring to make Spring save an instance of
	 * `ApplicationContext` into a static field accessible be the benchmarks spawned
	 * through the JMH runner.
	 *
	 * @param ApplicationContext wiredCntx
	 */
	@Autowired
	void setContext(ApplicationContext wiredCntx) {
		UserServiceJmhBenchmark.cntx = wiredCntx;

	}

	// this variable is set during the benchmarks setup and is accessible by the
	// benchmark method
	UserController controller;

	/*
	 * There is no @Test annotated method within here, because the AbstractBenchmark
	 * defines one, which spawns the JMH runner. This class only contains
	 * JMH/Benchmark related code.
	 */

	@Setup(Level.Trial)
	public void setupBenchmark() {

		controller = cntx.getBean(UserController.class);

	}

	@Benchmark
	public void measureUserLogin() throws UnsupportedEncodingException, NoSuchAlgorithmException {
		User u = controller.login("userName", "UserPassword", true);
		assertNotNull(u);

	}
}
