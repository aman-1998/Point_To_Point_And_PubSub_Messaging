package personal.learning.jms.consumer.wellness;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class WellnessConsumer2 implements MessageListener {

	@Override
	public void onMessage(Message message) {
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
