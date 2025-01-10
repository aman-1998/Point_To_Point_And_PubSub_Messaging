package personal.learning.jms;

import javax.jms.Session;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class JmsConfig {        
	
	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		//String brokerURL = "(tcp://localhost:61616,tcp://localhost:61617)";
		String brokerDiscoveryURL = "discovery:(zookeeper:(localhost:2181)/artemis-cluster)";
	    ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(brokerDiscoveryURL);
	    return factory;
	}
	
	@Bean("jmsTemplate")
	public JmsTemplate jmsTemplate(ActiveMQConnectionFactory connectionFactory) {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setSessionTransacted(true);
		return jmsTemplate;
	}
	
	@Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setSessionTransacted(true);
        factory.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
        factory.setAutoStartup(false); // Disable auto startup
        return factory;
    }
	
}
