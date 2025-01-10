package personal.learning.jms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import personal.learning.jms.consumer.payroll.Consumer1;
import personal.learning.jms.consumer.payroll.Consumer2;
import personal.learning.jms.publisher.DemoProducer;

@Configuration
@ComponentScan(basePackages = {"personal.learning.jms"})
public class AppConfig {
	
	@Bean
	public DemoProducer demoProducer() {
		return new DemoProducer();
	}
	
	@Bean
	public Consumer1 consumer1() {
		return new Consumer1();
	}
	
	@Bean
	public Consumer2 consumer2() {
		return new Consumer2();
	}
	
}
