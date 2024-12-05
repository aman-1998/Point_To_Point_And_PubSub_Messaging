package personal.learning.jms.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;

public class TopicPublisher {
	
	@Autowired
	@Qualifier("jmsTemplateWithoutDefaultDestinationPubSub")
	private JmsTemplate jmsTemplate;
	
	@Value("${springjms.myTopic.sampleTopic}")
	private String topic;
	
	public void publish(String message) {
		jmsTemplate.convertAndSend(topic, message);
	}
}
