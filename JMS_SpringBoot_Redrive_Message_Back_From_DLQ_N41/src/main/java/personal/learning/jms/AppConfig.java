package personal.learning.jms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import personal.learning.jms.listener.service.SampleListenerService;
import personal.learning.jms.receiver.DLQMessageReceiver;
import personal.learning.jms.receiver.SampleMessageReceiver;
import personal.learning.jms.sender.SampleMessageSender;

@Configuration
@ComponentScan(basePackages = {"personal.learning.jms"})
public class AppConfig {
	
	@Bean
	public SampleListenerService deliveryListenerService() {
		return new SampleListenerService();
	}
	
	@Bean
	public SampleMessageSender deliveryMessageSender() {
		return new SampleMessageSender();
	}
	
	@Bean
	public SampleMessageReceiver deliveryMessageReceiver() {
		return new SampleMessageReceiver();
	}
	
	@Bean
	public DLQMessageReceiver dlqMessageReceiver() {
		return new DLQMessageReceiver();
	}
	
}
