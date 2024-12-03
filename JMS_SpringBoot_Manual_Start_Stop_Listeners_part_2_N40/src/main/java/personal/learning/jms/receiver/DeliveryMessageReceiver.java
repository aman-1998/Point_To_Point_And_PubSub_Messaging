package personal.learning.jms.receiver;

import org.springframework.jms.annotation.JmsListener;

public class DeliveryMessageReceiver {
	
	@JmsListener(destination = "${springjms.myQueue.deliveryQueue}", id = "deleveryListener", containerFactory = "jmsListenerContainerFactory")
	public void receive(String message) {
		System.out.println("Message received by DeliveryMessageReceiver: " + message);
		
	}
}
