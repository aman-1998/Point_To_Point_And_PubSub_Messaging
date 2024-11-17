package personal.learning.jms.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;

public class TestMessageSender {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	public void send(String message) {
		jmsTemplate.convertAndSend(message);
	}
	
}
