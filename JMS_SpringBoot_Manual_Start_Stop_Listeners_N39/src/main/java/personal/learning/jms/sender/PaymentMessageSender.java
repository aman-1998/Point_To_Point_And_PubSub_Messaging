package personal.learning.jms.sender;

import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;

public class PaymentMessageSender {
	
	@Autowired
	@Qualifier("jmsTempDefaultDestination2")
	private JmsTemplate jmsTemplate;
	
	@Value("${springjms.myQueue.testQueue1}")
	private String queue;
	
	public void send(String message, long ttlMillis) {
		jmsTemplate.setExplicitQosEnabled(true); // Ensures TTL is explicitly applied
		jmsTemplate.setTimeToLive(ttlMillis);
		jmsTemplate.send(queue, session -> {
            Message msg = session.createTextMessage(message);
            return msg;
        });
	}
}
