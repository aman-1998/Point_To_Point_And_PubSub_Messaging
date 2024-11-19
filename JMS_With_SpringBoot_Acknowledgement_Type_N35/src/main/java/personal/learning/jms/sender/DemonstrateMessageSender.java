package personal.learning.jms.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;

public class DemonstrateMessageSender {
	
	@Autowired
	@Qualifier("jmsWithoutDefaultDestination")
	private JmsTemplate jmsTemplate;
	
	@Value("${springjms.myQueue.demonstrate}")
	private String queue;
	
	public void send(String message) {
		jmsTemplate.convertAndSend(queue, message);
	}
}
