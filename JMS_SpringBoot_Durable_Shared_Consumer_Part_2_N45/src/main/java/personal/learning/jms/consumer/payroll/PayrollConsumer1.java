package personal.learning.jms.consumer.payroll;

import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;

public class PayrollConsumer1 {

	@JmsListener(destination = "${springjms.myTopic.sampleTopic}", id = "payrollId1",
			     containerFactory = "jmsListenerContainerFactoryPayroll1", 
			     subscription = "sharedSubscriptionPayroll")
	public void receive(Message message) {
		try {
			if(message instanceof TextMessage) {
				String text = ((TextMessage) message).getText();
				System.out.println("Message received by PayrollConsumer1: " + text);
			}
		} catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Rollback triggered");
        }
	}

}
