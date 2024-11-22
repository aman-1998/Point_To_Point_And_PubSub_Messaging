package personal.learning.jms.receiver;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;

public class DemonstrateMessageReceiver {
	
	@JmsListener(destination = "${springjms.myQueue.demonstrate}", containerFactory = "jmsListenerContainerFactory")
	public void receive(Message message) {
		try {
			if(message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				System.out.println("Message received by DemonstrateMessageReceiver: " + textMessage.getText());
			}
			
			//message.acknowledge();
			System.out.println("Message not acknowledged");
		} catch(JMSException ex) {
			ex.printStackTrace();
		}
	}
}
