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
		return new ActiveMQConnectionFactory("tcp://localhost:61616");
	}
	
	@Bean("jmsTemplate1")
	public JmsTemplate jmsTemplate1(ActiveMQConnectionFactory connectionFactory) {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setSessionTransacted(true);
		jmsTemplate.setPubSubDomain(true);
		return jmsTemplate;
	}
	/*
	 * Youtube video: https://www.youtube.com/watch?v=VNbcksMm1Ow&list=PLaEq4mgXRLX9LIRY6pwwDhleVnqEjs3YK&index=1
	 * 
	 * In ActiveMQ Artemis, the default behavior for anycast routing is to deliver messages to queues 
	 * bound to an address in a round-robin manner. However, you can change this behavior by configuring 
	 * the routing type and queue settings. Here's how you can modify the routing behavior:
	 * 
	 * Message Distribution Policies:
	 * ------------------------------
	 * ActiveMQ Artemis supports message distribution policies. You can use these to alter how messages are 
     * distributed among multiple queues bound to an address.
     * 
     * Supported Distribution Policies:
     * ---------------------------------
     * OFF: Messages are routed to all matching queues without distribution.
     * STRICT: Ensures strict round-robin routing without skipping any queues.
     * RANDOM: Sends messages to queues in a random order.
     * 
     * To change the policy, you can configure the address-setting in the broker.xml:
	 * 
	 * <address-settings>
	 *	    <address-setting match="example.address">
	 *	        <distribution-policy>STRICT</distribution-policy>
	 *	    </address-setting>
	 *	</address-settings>
	 *
	 *
	 * Custom Routing Logic:
	 * -----------------------
     *
     * If the built-in distribution policies do not meet your needs, you can implement custom 
     * routing logic using a plugin. ActiveMQ Artemis allows you to write a custom plugin for 
     * message routing.
     * Example: Extend org.apache.activemq.artemis.core.server.plugin.ActiveMQServerPlugin and 
     * implement custom routing.
	 * 
	 */
	@Bean("jmsTemplate2")
	public JmsTemplate jmsTemplate2(ActiveMQConnectionFactory connectionFactory) {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setSessionTransacted(true);
		return jmsTemplate;
	}
	
	@Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory1(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setSessionTransacted(true);
        factory.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
        factory.setAutoStartup(false); // Disable auto startup
        return factory;
    }
	
	@Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory2(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setSessionTransacted(true);
        factory.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
        factory.setPubSubDomain(true);
        factory.setAutoStartup(false); // Disable auto startup
        return factory;
    }
	
}
