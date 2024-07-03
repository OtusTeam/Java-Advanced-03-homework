package otus.moryakovdv.task7.jmeter;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;

import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс, запускающий Jmeter engine в консоли без GUI.
 */
@Slf4j
public class EmbeddedJmeter {

	/**
	 * @param args - либо пустой, тогда параметра запуска JMeter по умолчанию, либо
	 *             три строки с путями к jmx, jmeter.properties, и самому jmeter
	 */
	public static void main(String[] args) {

		String jmxPath = "/home/moryakov/apache-jmeter-5.5/bin/task-7.jmx";
		String jmeterProperties = "/home/moryakov/apache-jmeter-5.5/bin/jmeter.properties";
		String jmeterHome = "/home/moryakov/apache-jmeter-5.5";

		StandardJMeterEngine engine = new StandardJMeterEngine();

		JMeterUtils.loadJMeterProperties(jmeterProperties);
		JMeterUtils.setJMeterHome(jmeterHome);

		JMeterUtils.initLocale();

		try {

			SaveService.loadProperties();
			File file = new File(jmxPath);
			HashTree testPlanTree = SaveService.loadTree(file);
			engine.configure(testPlanTree);
			engine.run();

		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}

}
