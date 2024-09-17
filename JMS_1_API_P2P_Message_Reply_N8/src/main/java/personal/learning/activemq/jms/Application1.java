package personal.learning.activemq.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
/*
 * Run Application1 first and Application2 second
 */
public class Application1 {
	
	public static void main(String[] args) {
		
		String brokerUrl = "tcp://localhost:61616";
		Connection connection = null;
		Session session = null;
		
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
			connection = connectionFactory.createConnection();
			session = connection.createSession();
			
			Queue queue = session.createQueue("queue5");
			
			MessageProducer producer = session.createProducer(queue);
			
			TextMessage textMessage = session.createTextMessage("Hello! how are you");
			producer.send(textMessage);
			System.out.println("Message sent : " + textMessage.getText());
			
			//---------------------------------------------------------------------------
			
			Queue replyQueue = session.createQueue("replyQueue");
			
			MessageConsumer consumer = session.createConsumer(replyQueue);
			
			connection.start();
			
			TextMessage receivedMessage = (TextMessage) consumer.receive();
			
			System.out.println("Message received : " + receivedMessage.getText());
			
			try {
				Thread.sleep(1200000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		} catch(JMSException ex) {
			
		}
	}
}
