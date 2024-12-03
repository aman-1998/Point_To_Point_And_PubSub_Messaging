package personal.learning.jms;

import javax.jms.Session;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import personal.learning.jms.receiver.OrderMessageReceiver;
import personal.learning.jms.receiver.PaymentMessageReceiver;

@Configuration
public class JmsConfig {
	
	@Value("${springjms.myQueue.testQueue}")
	private String testQueue;
	
	@Value("${springjms.myQueue.defaultTestQueue}")
	private String defaultTestQueue;
	
	@Value("${springjms.myQueue.testQueue1}")
	private String testQueue1;
	
	@Value("${springjms.myQueue.defaultTestQueue1}")
	private String defaultTestQueue1;

	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		return new ActiveMQConnectionFactory("tcp://localhost:61616");
	}
	
	@Bean("jmsTempDefaultDestination1")
	public JmsTemplate jmsTemplate3(ActiveMQConnectionFactory connectionFactory) {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setSessionTransacted(true);
		jmsTemplate.setDefaultDestinationName(defaultTestQueue);
		return jmsTemplate;
	}
	
	@Bean("jmsTempDefaultDestination2")
	public JmsTemplate jmsTemplate4(ActiveMQConnectionFactory connectionFactory) {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setSessionTransacted(true);
		jmsTemplate.setDefaultDestinationName(defaultTestQueue1);
		return jmsTemplate;
	}
	
	@Bean
	public DefaultMessageListenerContainer messageListenerContainer2(ActiveMQConnectionFactory connectionFactory, OrderMessageReceiver dummyMessageReceiver) {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setSessionTransacted(true);
		container.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
        container.setConnectionFactory(connectionFactory);
        container.setDestinationName(testQueue);
        container.setMessageListener(dummyMessageReceiver);
        container.setConcurrency("1-5"); // Optional: Set concurrency for processing
        return container;
	}
	
	@Bean
	public DefaultMessageListenerContainer messageListenerContainer3(ActiveMQConnectionFactory connectionFactory, PaymentMessageReceiver dummyMessageReceiver) {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setSessionTransacted(true);
		container.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
        container.setConnectionFactory(connectionFactory);
        container.setDestinationName(testQueue1);
        container.setAutoStartup(false); // Prevent container from starting automatically
        container.setMessageListener(dummyMessageReceiver);
        container.setConcurrency("1-5"); // Optional: Set concurrency for processing
        return container;
	}
}
