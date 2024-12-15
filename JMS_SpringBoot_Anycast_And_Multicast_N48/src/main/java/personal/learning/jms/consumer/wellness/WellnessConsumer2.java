package personal.learning.jms.consumer.wellness;

import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;

public class WellnessConsumer2 {

	@JmsListener(destination = "test.multicast.address2::TestingMultiQueue3", id = "wellnessId2",
		     containerFactory = "jmsListenerContainerFactory1")
	public void receive(Message message) {
		try {
			if(message instanceof TextMessage) {
				String text = ((TextMessage) message).getText();
				System.out.println("Message received by WellnessConsumer2: " + text);
			}
		} catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Rollback triggered");
        }
	}

}
