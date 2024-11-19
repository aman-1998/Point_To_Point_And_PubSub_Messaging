package personal.learning.jms.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;

public class TestMessageSender {
	
	@Autowired
	@Qualifier("jmsWithDefaultDestination")
	private JmsTemplate jmsTemplate;
	
	public void send(String message) {
		jmsTemplate.convertAndSend(message);
	}
	
}
