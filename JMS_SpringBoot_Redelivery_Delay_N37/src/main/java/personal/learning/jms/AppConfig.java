package personal.learning.jms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import personal.learning.jms.receiver.DummyMessageReceiver;
import personal.learning.jms.sender.DummyMessageSender;

@Configuration
@ComponentScan(basePackages = {"personal.learning.jms"})
public class AppConfig {
	
	@Bean
	public DummyMessageSender dummyMessageSender() {
		return new DummyMessageSender();
	}
	
	@Bean
	public DummyMessageReceiver dummyMessageReceiver() {
		return new DummyMessageReceiver();
	}
	
}
