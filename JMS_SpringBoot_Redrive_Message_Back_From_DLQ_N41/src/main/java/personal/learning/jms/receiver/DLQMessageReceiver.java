package personal.learning.jms.receiver;

import org.springframework.jms.annotation.JmsListener;

public class DLQMessageReceiver {
	
	@JmsListener(destination = "DLQ", containerFactory = "jmsListenerContainerFactoryForDLQ")
	public void receive(String message) {
		System.out.println("Message received by DLQMessageReceiver: " + message);
	}
}
