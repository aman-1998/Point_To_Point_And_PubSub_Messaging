package personal.learning.jms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import personal.learning.jms.listener.service.JMSListenerService;
import personal.learning.jms.publisher.TopicPublisher;
import personal.learning.jms.receiver.DLQMessageReceiver;
import personal.learning.jms.receiver.SampleMessageReceiver;
import personal.learning.jms.sender.SampleMessageSender;
import personal.learning.jms.subscriber.TopicSubscriber1;
import personal.learning.jms.subscriber.TopicSubscriber2;
import personal.learning.jms.subscriber.TopicSubscriber3;

@Configuration
@ComponentScan(basePackages = {"personal.learning.jms"})
public class AppConfig {
	
	@Bean
	public JMSListenerService deliveryListenerService() {
		return new JMSListenerService();
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
	
	@Bean
	public TopicPublisher topicPublisher() {
		return new TopicPublisher();
	}
	
	@Bean
	public TopicSubscriber1 topicSubscriber1() {
		return new TopicSubscriber1();
	}
	
	@Bean
	public TopicSubscriber2 topicSubscriber2() {
		return new TopicSubscriber2();
	}
	
	@Bean
	public TopicSubscriber3 topicSubscriber3() {
		return new TopicSubscriber3();
	}
	
}
