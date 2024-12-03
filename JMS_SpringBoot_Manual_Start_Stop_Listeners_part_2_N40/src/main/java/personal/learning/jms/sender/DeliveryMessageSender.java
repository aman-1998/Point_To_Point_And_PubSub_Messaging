package personal.learning.jms.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

public class DeliveryMessageSender {
	
	@Autowired
	@Qualifier("jmsTempDefaultDestination3")
	private JmsTemplate jmsTemplate;
	
	@Value("${springjms.myQueue.deliveryQueue}")
	private String queue;
	
	public void send(String message) {
		jmsTemplate.convertAndSend(queue, message);
	}
}
