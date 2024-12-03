package personal.learning.jms.receiver;

import org.springframework.jms.annotation.JmsListener;

public class SampleMessageReceiver {
	
	@JmsListener(destination = "${springjms.myQueue.sampleQueue}", id = "sampleListener", containerFactory = "jmsListenerContainerFactory")
	public void receive(String message) {
		
		System.out.println("Message received by SampleMessageReceiver: " + message);
		try {
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
