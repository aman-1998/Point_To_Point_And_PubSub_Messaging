package personal.learning.jms.receiver;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/*
 * Initial Delivery Delay (_AMQ_SCHED_DELIVERY):
 * This determines when a message will first become available for delivery. 
 * Until the delay expires, the message is held in the broker's scheduled 
 * messages queue and is not considered eligible for expiry.
 * 
 * 
 * TTL (Time-to-Live):
 * TTL is calculated once the message is sent to the broker. However, TTL countdown 
 * starts only after the message is delivered to the consumer and becomes eligible 
 * for delivery (i.e., after the initial delay expires).
 */

public class PaymentMessageReceiver implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			if(message instanceof TextMessage) {
				String text = ((TextMessage) message).getText();
				System.out.println("Message received by PaymentMessageReceiver : " + text);
			}
			
		} catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Rollback triggered");
        }
	}

}
