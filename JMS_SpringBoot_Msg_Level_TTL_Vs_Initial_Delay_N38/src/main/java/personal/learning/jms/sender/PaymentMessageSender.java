package personal.learning.jms.sender;

import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;

/*
 * Initial Delivery Delay (_AMQ_SCHED_DELIVERY):
 * This determines when a message will first become available for delivery. 
 * Until the delay expires, the message is held in the broker's scheduled 
 * messages queue and is not considered eligible for expiry.
 * 
 * 
 * TTL (Time-to-Live):
 * TTL is calculated once the message is sent to the broker.
 */

public class PaymentMessageSender {
	
	@Autowired
	@Qualifier("jmsTempDefaultDestination2")
	private JmsTemplate jmsTemplate;
	
	@Value("${springjms.myQueue.testQueue1}")
	private String queue;
	
	public void send(String message, long ttlMillis, long initialDelayMillis) {
		jmsTemplate.setExplicitQosEnabled(true); // Ensures TTL is explicitly applied
		jmsTemplate.setTimeToLive(ttlMillis);
		jmsTemplate.send(queue, session -> {
            Message msg = session.createTextMessage(message);
            msg.setLongProperty("_AMQ_SCHED_DELIVERY", System.currentTimeMillis() + initialDelayMillis); // Set initial delivery delay
            return msg;
        });
	}
}
