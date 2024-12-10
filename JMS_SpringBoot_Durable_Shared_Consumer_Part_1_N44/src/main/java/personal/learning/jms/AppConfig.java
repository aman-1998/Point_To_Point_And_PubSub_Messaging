package personal.learning.jms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import personal.learning.jms.consumer.payroll.PayrollConsumer1;
import personal.learning.jms.consumer.payroll.PayrollConsumer2;
import personal.learning.jms.consumer.security.SecurityConsumer1;
import personal.learning.jms.consumer.security.SecurityConsumer2;
import personal.learning.jms.consumer.wellness.WellnessConsumer1;
import personal.learning.jms.consumer.wellness.WellnessConsumer2;
import personal.learning.jms.publisher.HRMSTopicPublisher;

@Configuration
@ComponentScan(basePackages = {"personal.learning.jms"})
public class AppConfig {
	
	@Bean
	public HRMSTopicPublisher hrmsTopicPublisher() {
		return new HRMSTopicPublisher();
	}
	
	@Bean
	public PayrollConsumer1 payrollConsumer1() {
		return new PayrollConsumer1();
	}
	
	@Bean
	public PayrollConsumer2 payrollConsumer2() {
		return new PayrollConsumer2();
	}
	
	@Bean
	public SecurityConsumer1 securityConsumer1() {
		return new SecurityConsumer1();
	}
	
	@Bean
	public SecurityConsumer2 securityConsumer2() {
		return new SecurityConsumer2();
	}
	
	@Bean
	public WellnessConsumer1 wellnessConsumer1() {
		return new WellnessConsumer1();
	}
	
	@Bean
	public WellnessConsumer2 wellnessConsumer2() {
		return new WellnessConsumer2();
	}
	
}
