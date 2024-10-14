package otus.moryakovdv.task17.jmeter;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс, запускающий Jmeter engine в консоли без GUI.
 */
@Slf4j
public class EmbeddedJmeter {

	/**
	 * @param args - либо пустой, тогда параметра запуска JMeter по умолчанию, либо
	 * три строки с путями к jmx, jmeter.properties, и самому jmeter.
	 * 
	 * В конфигурации Jmeter указана опция 
	 *   jmeterengine.force.system.exit=true
	 *	 но работает она не всегда и есть риск подвисшего потока тестирования.
	 *
	 *   Поэтому выполнение потока тестирования прерывается по таймауту CountDownLatch.	
	 */
	public static void main(String[] args) {

		int duration = 65000;
		String jmxPath = "./task17.jmx";
		String jmeterProperties = "./jmeter.properties";
		String jmeterHome = "/home/moryakov/apache-jmeter-5.5";

		JMeterUtils.loadJMeterProperties(jmeterProperties);

		JMeterUtils.setJMeterHome(jmeterHome);

		JMeterUtils.initLocale();

		StandardJMeterEngine engine = new StandardJMeterEngine();

		CountDownLatch latch = new CountDownLatch(1);

		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<?> testing;
		Runnable r = new Runnable() {

			@Override
			public void run() {
				try {
					SaveService.loadProperties();

					File file = new File(jmxPath);
					HashTree testPlanTree = SaveService.loadTree(file);
					engine.configure(testPlanTree);

					engine.run();
				} catch (Exception e) {
					log.error(e.getMessage());
					Thread.currentThread().interrupt();

				} finally {
					latch.countDown();
				}

			}
		};

		try {

			testing = executor.submit(r);
			latch.await(duration, TimeUnit.MILLISECONDS);
			testing.cancel(true);

		} catch (InterruptedException e) {
			log.error(e.getMessage());
			Thread.currentThread().interrupt();
		} finally {
			
			engine.stopTest(true);
			StandardJMeterEngine.stopEngineNow();
			
			log.info("Testing completed");
			
			System.exit(0);
			
			
		}

	}

}
