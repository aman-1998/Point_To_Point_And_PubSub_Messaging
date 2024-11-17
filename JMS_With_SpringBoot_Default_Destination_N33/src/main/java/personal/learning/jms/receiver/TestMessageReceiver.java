package personal.learning.jms.receiver;

import org.springframework.jms.annotation.JmsListener;

public class TestMessageReceiver {
	
	@JmsListener(destination = "${springjms.myQueue}")
	public void receive(String message) {
		System.out.println("Message received ====> " + message);
	}
}
