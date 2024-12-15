package personal.learning.jms.consumer.payroll;

import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;

public class PayrollConsumer2 {

	@JmsListener(destination = "test.multicast.address1::TestingMultiQueue1", id = "payrollId2",
		     containerFactory = "jmsListenerContainerFactory1")
	public void receive(Message message) {
		try {
			if(message instanceof TextMessage) {
				String text = ((TextMessage) message).getText();
				System.out.println("Message received by PayrollConsumer2: " + text);
			}
		} catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Rollback triggered");
        }
	}

}
