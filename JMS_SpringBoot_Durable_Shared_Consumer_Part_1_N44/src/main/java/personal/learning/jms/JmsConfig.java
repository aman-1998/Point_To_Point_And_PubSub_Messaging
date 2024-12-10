package personal.learning.jms;

import javax.jms.Session;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import personal.learning.jms.consumer.payroll.PayrollConsumer1;
import personal.learning.jms.consumer.payroll.PayrollConsumer2;
import personal.learning.jms.consumer.security.SecurityConsumer1;
import personal.learning.jms.consumer.security.SecurityConsumer2;
import personal.learning.jms.consumer.wellness.WellnessConsumer1;
import personal.learning.jms.consumer.wellness.WellnessConsumer2;

@Configuration
public class JmsConfig {
	
	@Value("${springjms.myTopic.sampleTopic}")
	public String topic;
	
	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		return new ActiveMQConnectionFactory("tcp://localhost:61616");
	}
	
	@Bean("jmsTemplateWithoutDefaultDestinationPubSub")
	public JmsTemplate jmsTemplate2(ActiveMQConnectionFactory connectionFactory) {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setSessionTransacted(true);
		jmsTemplate.setPubSubDomain(true);
		return jmsTemplate;
	}
	
	/*
	 * The behavior you're observing arises because Spring's JMS support does not directly 
	 * implement shared consumers for topics as defined by the JMS 2.0 shared subscription model. 
	 * Instead, it creates individual consumers that receive messages independently. 
	 * This limitation is particularly relevant for shared subscriptions in ActiveMQ Artemis 
	 * when using Spring's DefaultJmsListenerContainerFactory or DefaultMessageListenerContainer.
	 *
	 * Why It Works with ActiveMQ Artemis Core API:
	 * When you use ActiveMQ Artemis Core API, you directly create a shared consumer by explicitly 
	 * specifying the subscription name and client ID in a way that complies with JMS 2.0 shared 
	 * subscription semantics. The broker ensures that only one of the shared consumers receives the message.
	 */
	@Bean
	public DefaultMessageListenerContainer messageListenerContainerPay1(ActiveMQConnectionFactory connectionFactory, PayrollConsumer1 payrollConsumer1) {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setSessionTransacted(true);
		container.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
        container.setDestinationName(topic);
        container.setMessageListener(payrollConsumer1);
        container.setPubSubDomain(true); // Enable PubSub for Topics
        container.setSubscriptionDurable(true); 
        container.setDurableSubscriptionName("sharedSubscriptionPayroll"); // Shared durable subscription
        container.setClientId("client1"); // Unique client ID for this consumer
        container.setAutoStartup(false); // Prevent container from starting automatically
        return container;
	}
	
	@Bean
	public DefaultMessageListenerContainer messageListenerContainerPay2(ActiveMQConnectionFactory connectionFactory, PayrollConsumer2 payrollConsumer2) {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setSessionTransacted(true);
		container.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
        container.setDestinationName(topic);
        container.setMessageListener(payrollConsumer2);
        container.setPubSubDomain(true); // Enable PubSub for Topics
        container.setSubscriptionDurable(true); 
        container.setDurableSubscriptionName("sharedSubscriptionPayroll"); // Shared durable subscription
        container.setClientId("client2"); // Unique client ID for this consumer
        container.setAutoStartup(false); // Prevent container from starting automatically
        return container;
	}
	
	@Bean
	public DefaultMessageListenerContainer messageListenerContainerSec1(ActiveMQConnectionFactory connectionFactory, SecurityConsumer1 securityConsumer1) {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setSessionTransacted(true);
		container.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
        container.setDestinationName(topic);
        container.setMessageListener(securityConsumer1);
        container.setPubSubDomain(true); // Enable PubSub for Topics
        container.setSubscriptionDurable(true); 
        container.setDurableSubscriptionName("sharedSubscriptionSecurity"); // Shared durable subscription
        container.setClientId("client3"); // Unique client ID for this consumer
        container.setAutoStartup(false); // Prevent container from starting automatically
        return container;
	}
	
	@Bean
	public DefaultMessageListenerContainer messageListenerContainerSec2(ActiveMQConnectionFactory connectionFactory, SecurityConsumer2 securityConsumer2) {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setSessionTransacted(true);
		container.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
        container.setDestinationName(topic);
        container.setMessageListener(securityConsumer2);
        container.setPubSubDomain(true); // Enable PubSub for Topics
        container.setSubscriptionDurable(true); 
        container.setDurableSubscriptionName("sharedSubscriptionSecurity"); // Shared durable subscription
        container.setClientId("client4"); // Unique client ID for this consumer
        container.setAutoStartup(false); // Prevent container from starting automatically
        return container;
	}
	
	@Bean
	public DefaultMessageListenerContainer messageListenerContainerWell1(ActiveMQConnectionFactory connectionFactory, WellnessConsumer1 wellnessConsumer1) {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setSessionTransacted(true);
		container.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
        container.setDestinationName(topic);
        container.setMessageListener(wellnessConsumer1);
        container.setPubSubDomain(true); // Enable PubSub for Topics
        container.setSubscriptionDurable(true); 
        container.setDurableSubscriptionName("sharedSubscriptionWellness"); // Shared durable subscription
        container.setClientId("client5"); // Unique client ID for this consumer
        container.setAutoStartup(false); // Prevent container from starting automatically
        return container;
	}
	
	@Bean
	public DefaultMessageListenerContainer messageListenerContainerWell2(ActiveMQConnectionFactory connectionFactory, WellnessConsumer2 wellnessConsumer2) {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setSessionTransacted(true);
		container.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
        container.setDestinationName(topic);
        container.setMessageListener(wellnessConsumer2);
        container.setPubSubDomain(true); // Enable PubSub for Topics
        container.setSubscriptionDurable(true); 
        container.setDurableSubscriptionName("sharedSubscriptionWellness"); // Shared durable subscription
        container.setClientId("client6"); // Unique client ID for this consumer
        container.setAutoStartup(false); // Prevent container from starting automatically
        return container;
	}
	
	
}
