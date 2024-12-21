package personal.learning.jms.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;

public class DemoProducer {
	
	@Autowired
	@Qualifier("jmsTemplate")
	private JmsTemplate jmsTemplate;
	
	@Value("exampleQueue")
	private String topic;
	
	public void send(String message) {
		jmsTemplate.convertAndSend(topic, message);
	}
	
}
