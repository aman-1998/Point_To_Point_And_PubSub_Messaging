package personal.learning.jms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import personal.learning.jms.receiver.TestMessageReceiver;
import personal.learning.jms.sender.TestMessageSender;

@Configuration
@ComponentScan(basePackages = {"personal.learning.jms"})
public class AppConfig {
	
	@Bean
	public TestMessageReceiver testMessageReceiver() {
		return new TestMessageReceiver();
	}
	
	@Bean
	public TestMessageSender testMessageSender() {
		return new TestMessageSender();
	}

}
