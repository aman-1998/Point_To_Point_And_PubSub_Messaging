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
public class Application2 {
	
	public static void main(String[] args) {
		
		String brokerUrl = "tcp://localhost:61616";
		Connection connection = null;
		Session session = null;
		
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
			connection = connectionFactory.createConnection();
			session = connection.createSession();
			
			Queue queue = session.createQueue("queue6");
			
			MessageConsumer consumer = session.createConsumer(queue);
			
			connection.start();
			
			TextMessage textMessage = (TextMessage) consumer.receive();
			
			System.out.println("Message received by App2 : " + textMessage.getText());
			
			//--------------------------------------------------------------------------------
			
			MessageProducer producer = session.createProducer(textMessage.getJMSReplyTo());
			
			TextMessage replyMessage = session.createTextMessage("I am fine! I am going to the market");
			replyMessage.setJMSCorrelationID(textMessage.getJMSMessageID());
			
			producer.send(replyMessage);
			
			System.out.println("Message sent by App2 : " + replyMessage.getText());
			
			try {
				Thread.sleep(1200000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		} catch(JMSException ex) {
			
		}
	}
}