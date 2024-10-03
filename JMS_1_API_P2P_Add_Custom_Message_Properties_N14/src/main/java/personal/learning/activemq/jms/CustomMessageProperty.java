package personal.learning.activemq.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TemporaryQueue;
import javax.jms.TextMessage;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
/*
 * Run Application1 first and Application2 second
 */
public class CustomMessageProperty {
	
	public static void main(String[] args) {
		
		String brokerUrl = "tcp://localhost:61616";
		Connection connection = null;
		Session session = null;
		try {
			
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
			connection = connectionFactory.createConnection();
			session = connection.createSession();
			
			Queue queue = session.createQueue("queue7");
			
			MessageProducer messageProducer = session.createProducer(queue);
			
			TextMessage textMessage = session.createTextMessage("Krishna says! Protect Dharma and it will protect you");
			textMessage.setBooleanProperty("testBoolean", true);
			textMessage.setStringProperty("testString", "Testing String property");
			textMessage.setIntProperty("intProperty", 18);
			
			messageProducer.send(textMessage);
			
			System.out.println("Message sent : " + textMessage.getText());
			
			//--------------------------------------------------------------------------------------
			
			MessageConsumer consumer = session.createConsumer(queue);
			
			connection.start();
			
			TextMessage receivedMessage = (TextMessage) consumer.receive();
			
			System.out.println("Message received : " + receivedMessage.getText());
			System.out.println("testBoolean : " + textMessage.getBooleanProperty("testBoolean"));
			System.out.println("testString : " + textMessage.getStringProperty("testString"));
			System.out.println("intProperty : " + textMessage.getIntProperty("intProperty"));
			
			
		} catch(JMSException ex) {
			ex.printStackTrace();
		} finally {
			try {
				
				if(connection != null) {
					connection.close();
				}
				
				if(session != null) {
					session.close();
				}
				
			} catch(JMSException ex) {
				ex.printStackTrace();
			}
		}
	}
}
