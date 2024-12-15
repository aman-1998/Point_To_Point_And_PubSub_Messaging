package personal.learning.jms.consumer.project;

import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;

public class ProjectAllotment1 {

	@JmsListener(destination = "test.anycast.address1::TestingQueue1", id = "projectAllotment1",
			     containerFactory = "jmsListenerContainerFactory1")
	public void receive(Message message) {
		try {
			if(message instanceof TextMessage) {
				String text = ((TextMessage) message).getText();
				System.out.println("Message received by ProjectAllotment1: " + text);
			}
		} catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Rollback triggered");
        }
	}

}
