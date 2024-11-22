package personal.learning.jms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import personal.learning.jms.receiver.DemoMessageReceiver;
import personal.learning.jms.receiver.DemonstrateMessageReceiver;
import personal.learning.jms.receiver.DummyMessageReceiver;
import personal.learning.jms.receiver.TestMessageReceiver;
import personal.learning.jms.sender.DemoMessageSender;
import personal.learning.jms.sender.DemonstrateMessageSender;
import personal.learning.jms.sender.DummyMessageSender;
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
	
	@Bean
	public DemoMessageReceiver demoMessageReceiver() {
		return new DemoMessageReceiver();
	}
	
	@Bean
	public DemoMessageSender demoMessageSender() {
		return new DemoMessageSender();
	}
	
	@Bean
	public DummyMessageSender dummyMessageSender() {
		return new DummyMessageSender();
	}
	
	@Bean
	public DummyMessageReceiver dummyMessageReceiver() {
		return new DummyMessageReceiver();
	}
	
	@Bean
	public DemonstrateMessageSender demonstrateMessageSender() {
		return new DemonstrateMessageSender();
	}
	
	@Bean
	public DemonstrateMessageReceiver demonstrateMessageReceiver() {
		return new DemonstrateMessageReceiver();
	}

}
