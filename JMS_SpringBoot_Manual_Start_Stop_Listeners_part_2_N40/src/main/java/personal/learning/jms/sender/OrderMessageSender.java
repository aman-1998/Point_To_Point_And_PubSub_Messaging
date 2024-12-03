package personal.learning.jms.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;

public class OrderMessageSender {
	
	@Autowired
	@Qualifier("jmsTempDefaultDestination1")
	private JmsTemplate jmsTemplate;
	
	@Value("${springjms.myQueue.testQueue}")
	private String queue;
	
	public void send(String message) {
		jmsTemplate.convertAndSend(queue, message);
	}
}
