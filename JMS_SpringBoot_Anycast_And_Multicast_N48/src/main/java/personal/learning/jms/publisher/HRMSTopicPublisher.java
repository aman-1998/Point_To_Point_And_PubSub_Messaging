package personal.learning.jms.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;

public class HRMSTopicPublisher {
	
	@Autowired
	@Qualifier("jmsTemplate1")
	private JmsTemplate jmsTemplate1;
	
	@Autowired
	@Qualifier("jmsTemplate2")
	private JmsTemplate jmsTemplate2;
	
	@Value("test.multicast.address1")
	private String topic1;
	
	@Value("test.multicast.address2")
	private String topic2;
	
	@Value("test.anycast.address1")
	private String topic3;
	
	public void publish1(String message) {
		jmsTemplate1.convertAndSend(topic1, message);
	}
	
	public void publish2(String message) {
		jmsTemplate1.convertAndSend(topic2, message);
	}
	
	public void publish3(String message) {
		jmsTemplate2.convertAndSend(topic3, message);
	}
}
