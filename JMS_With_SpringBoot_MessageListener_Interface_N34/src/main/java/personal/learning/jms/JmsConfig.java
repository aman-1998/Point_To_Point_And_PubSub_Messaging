package personal.learning.jms;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import personal.learning.jms.receiver.DemoMessageReceiver;
import personal.learning.jms.receiver.DummyMessageReceiver;

@Configuration
public class JmsConfig {
	
	@Value("${springjms.myQueue}")
	private String queue;
	
	@Value("${springjms.myQueue.test1}")
	private String demoQueue;
	
	@Value("${springjms.myQueue.test2}")
	private String dummyQueue;
	
	@Value("${springjms.myQueue.test2.default}")
	private String defaultDummyQueue;

	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		return new ActiveMQConnectionFactory("tcp://localhost:61616");
	}
	
	@Bean("jmsWithDefaultDestination")
	public JmsTemplate jmsTemplate1(ActiveMQConnectionFactory connectionFactory) {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setDefaultDestinationName(queue);
		return jmsTemplate;
	}
	
	@Bean("jmsWithoutDefaultDestination")
	public JmsTemplate jmsTemplate2(ActiveMQConnectionFactory connectionFactory) {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		//jmsTemplate.setDefaultDestinationName(queue);
		return jmsTemplate;
	}
	
	@Bean("jmsDefaultDummyDestination")
	public JmsTemplate jmsTemplate3(ActiveMQConnectionFactory connectionFactory) {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setDefaultDestinationName(defaultDummyQueue);
		return jmsTemplate;
	}
	
	@Bean
	public DefaultMessageListenerContainer messageListenerContainer1(ActiveMQConnectionFactory connectionFactory, DemoMessageReceiver demoMessageReceiver) {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setDestinationName(demoQueue);
        container.setMessageListener(demoMessageReceiver);
        container.setConcurrency("1-5"); // Optional: Set concurrency for processing
        return container;
	}
	
	@Bean
	public DefaultMessageListenerContainer messageListenerContainer2(ActiveMQConnectionFactory connectionFactory, DummyMessageReceiver dummyMessageReceiver) {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setDestinationName(dummyQueue);
        container.setMessageListener(dummyMessageReceiver);
        container.setConcurrency("1-5"); // Optional: Set concurrency for processing
        return container;
	}
	
}
