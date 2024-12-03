package personal.learning.jms.sender;

import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

public class SampleMessageSender {
	
	@Autowired
	@Qualifier("jmsTemplateWithoutDefaultDestination")
	private JmsTemplate jmsTemplate;
	
	@Value("${springjms.myQueue.sampleQueue}")
	private String queue;
	
	public void send(String message, long initialDelayMillis) {
		jmsTemplate.send(queue, session -> {
            Message msg = session.createTextMessage(message);
            msg.setLongProperty("_AMQ_SCHED_DELIVERY", System.currentTimeMillis() + initialDelayMillis); // Set initial delivery delay
            return msg;
        });
	}
}
