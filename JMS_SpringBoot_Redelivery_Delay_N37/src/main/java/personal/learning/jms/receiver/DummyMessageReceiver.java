package personal.learning.jms.receiver;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

public class DummyMessageReceiver implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			if(message instanceof TextMessage) {
				String text = ((TextMessage) message).getText();
				System.out.println("Message received by DummyMessageReceiver : " + text);
			}
			
			System.out.println("====> Session rolled back <====");
			// Simulate processing error to test rollback
            throw new RuntimeException("Simulated exception to test rollback");
			
		} catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Rollback triggered");
        }
	}

}
