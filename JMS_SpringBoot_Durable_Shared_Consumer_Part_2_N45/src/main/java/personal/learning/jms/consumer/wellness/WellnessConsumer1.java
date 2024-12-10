package personal.learning.jms.consumer.wellness;

import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;

public class WellnessConsumer1 {

	@JmsListener(destination = "${springjms.myTopic.sampleTopic}", id = "wellnessId1",
			     containerFactory = "jmsListenerContainerFactoryWellness1", 
			     subscription = "sharedSubscriptionWellness")
	public void receive(Message message) {
		try {
			if(message instanceof TextMessage) {
				String text = ((TextMessage) message).getText();
				System.out.println("Message received by WellnessConsumer1: " + text);
			}
		} catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Rollback triggered");
        }
	}

}
