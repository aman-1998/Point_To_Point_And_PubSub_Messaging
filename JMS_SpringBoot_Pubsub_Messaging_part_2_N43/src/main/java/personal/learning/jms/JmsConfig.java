package personal.learning.jms;

import javax.jms.Session;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import personal.learning.jms.subscriber.TopicSubscriber1;
import personal.learning.jms.subscriber.TopicSubscriber2;
import personal.learning.jms.subscriber.TopicSubscriber3;

@Configuration
public class JmsConfig {
	
	@Value("${springjms.myTopic.sampleTopic}")
	public String topic;
	
	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		return new ActiveMQConnectionFactory("tcp://localhost:61616");
	}
	
	@Bean("jmsTemplateWithoutDefaultDestination")
	public JmsTemplate jmsTemplate1(ActiveMQConnectionFactory connectionFactory) {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setSessionTransacted(true);
		return jmsTemplate;
	}
	
	@Bean("jmsTemplateWithoutDefaultDestinationPubSub")
	public JmsTemplate jmsTemplate2(ActiveMQConnectionFactory connectionFactory) {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setSessionTransacted(true);
		jmsTemplate.setPubSubDomain(true);
		return jmsTemplate;
	}
	
	@Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setSessionTransacted(true);
        factory.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
        factory.setConnectionFactory(connectionFactory);
        factory.setAutoStartup(false); // Disable auto startup
        return factory;
    }
	
	@Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactoryForDLQ(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setSessionTransacted(true);
        factory.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
        //factory.setAutoStartup(false); // Disable auto startup
        return factory;
    }
	
	@Bean
	public DefaultMessageListenerContainer messageListenerContainer1(ActiveMQConnectionFactory connectionFactory, TopicSubscriber1 topicSubscriber1) {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setSessionTransacted(true);
		container.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
        container.setDestinationName(topic);
        container.setMessageListener(topicSubscriber1);
        container.setPubSubDomain(true); // Enable PubSub for Topics
        container.setAutoStartup(false); // Prevent container from starting automatically
        return container;
	}
	
	@Bean
	public DefaultMessageListenerContainer messageListenerContainer2(ActiveMQConnectionFactory connectionFactory, TopicSubscriber2 topicSubscriber2) {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setSessionTransacted(true);
		container.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
        container.setDestinationName(topic);
        container.setMessageListener(topicSubscriber2);
        container.setPubSubDomain(true); // Enable PubSub for Topics
        container.setAutoStartup(false); // Prevent container from starting automatically
        return container;
	}
	
	@Bean
	public DefaultMessageListenerContainer messageListenerContainer3(ActiveMQConnectionFactory connectionFactory, TopicSubscriber3 topicSubscriber3) {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setSessionTransacted(true);
		container.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
        container.setDestinationName(topic);
        container.setMessageListener(topicSubscriber3);
        container.setPubSubDomain(true); // Enable PubSub for Topics
        container.setAutoStartup(false); // Prevent container from starting automatically
        return container;
	}
}
