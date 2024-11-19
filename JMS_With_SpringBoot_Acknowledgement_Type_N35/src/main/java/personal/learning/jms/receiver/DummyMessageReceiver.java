package personal.learning.jms.receiver;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class DummyMessageReceiver implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			if(message instanceof TextMessage) {
				String text = ((TextMessage) message).getText();
				System.out.println("Message received by DummyMessageReceiver : " + text);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
