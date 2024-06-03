package otus.moryakovdv.task3;

import org.apache.poi.xwpf.usermodel.XWPFChart;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.google.gwt.user.client.*;
import com.google.gwt.user.client.rpc.core.java.lang.NullPointerException_CustomFieldSerializer;
import com.mysql.cj.jdbc.MysqlDataSource;


import org.hibernate.*;

@SpringBootApplication

@Configuration
@EnableAutoConfiguration

@EnableJpaRepositories
@ComponentScan
public class WebApplication {

	
	
	public static void main(String[] args) throws Exception {
		
		//includeSomeTrashForCDSTesting();
		
		SpringApplication.run(WebApplication.class, args);
	}
	
	
	@SuppressWarnings("unused")
	private static void includeSomeTrashForCDSTesting() {
		
		

	}


}
