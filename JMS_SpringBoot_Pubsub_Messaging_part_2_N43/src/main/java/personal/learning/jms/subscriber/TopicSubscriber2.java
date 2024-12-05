package personal.learning.jms.subscriber;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TopicSubscriber2 implements MessageListener {
	
	@Override
	public void onMessage(Message message) {
		try {
			if(message instanceof TextMessage) {
				String text = ((TextMessage) message).getText();
				System.out.println("Subscriber 2 received: " + text);
			}
		} catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Rollback triggered");
        }
	}
}
