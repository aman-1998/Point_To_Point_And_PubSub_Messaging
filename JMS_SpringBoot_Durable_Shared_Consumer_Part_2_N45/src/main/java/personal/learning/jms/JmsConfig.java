package personal.learning.jms;

import javax.jms.Session;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

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
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactoryPayroll1(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setSessionTransacted(true);
        factory.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
        factory.setPubSubDomain(true); // Enable PubSub for Topics
        factory.setSubscriptionDurable(true); // Enable durable subscription
        factory.setAutoStartup(false); // Disable auto startup
        factory.setClientId("clientId1");
        return factory;
    }
	
	@Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactoryPayroll2(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setSessionTransacted(true);
        factory.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
        factory.setPubSubDomain(true); // Enable PubSub for Topics
        factory.setSubscriptionDurable(true); // Enable durable subscription
        factory.setAutoStartup(false); // Disable auto startup
        factory.setClientId("clientId2");
        return factory;
    }
	
	@Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactorySecurity1(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setSessionTransacted(true);
        factory.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
        factory.setPubSubDomain(true); // Enable PubSub for Topics
        factory.setSubscriptionDurable(true); // Enable durable subscription
        factory.setAutoStartup(false); // Disable auto startup
        factory.setClientId("clientId3");
        return factory;
    }
	
	@Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactorySecurity2(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setSessionTransacted(true);
        factory.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
        factory.setPubSubDomain(true); // Enable PubSub for Topics
        factory.setSubscriptionDurable(true); // Enable durable subscription
        factory.setAutoStartup(false); // Disable auto startup
        factory.setClientId("clientId4");
        return factory;
    }
	
	@Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactoryWellness1(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setSessionTransacted(true);
        factory.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
        factory.setPubSubDomain(true); // Enable PubSub for Topics
        factory.setSubscriptionDurable(true); // Enable durable subscription
        factory.setAutoStartup(false); // Disable auto startup
        factory.setClientId("clientId5");
        return factory;
    }
	
	@Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactoryWellness2(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setSessionTransacted(true);
        factory.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
        factory.setPubSubDomain(true); // Enable PubSub for Topics
        factory.setSubscriptionDurable(true); // Enable durable subscription
        factory.setAutoStartup(false); // Disable auto startup
        factory.setClientId("clientId6");
        return factory;
    }
	
}
