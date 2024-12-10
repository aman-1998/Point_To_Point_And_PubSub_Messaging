package personal.learning.jms.consumer.security;

import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;

public class SecurityConsumer1 {
	
	@JmsListener(destination = "${springjms.myTopic.sampleTopic}", id = "securityId1",
				 containerFactory = "jmsListenerContainerFactorySecurity1", 
				 subscription = "sharedSubscriptionSecurity")
	public void receive(Message message) {
		try {
			if(message instanceof TextMessage) {
				String text = ((TextMessage) message).getText();
				System.out.println("Message received by SecurityConsumer1: " + text);
			}
		} catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Rollback triggered");
        }
	}

}
