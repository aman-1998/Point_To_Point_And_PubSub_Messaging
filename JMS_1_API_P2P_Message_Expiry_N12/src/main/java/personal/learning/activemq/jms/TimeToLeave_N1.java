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
public class TimeToLeave_N1 {
	
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
			messageProducer.setTimeToLive(2000);
			
			TextMessage textMessage = session.createTextMessage("Krishna says! Protect Dharma and it will protect you");
			
			messageProducer.send(textMessage);
			
			System.out.println("Message sent : " + textMessage.getText());
			
			//-------------------------------------------------------------------------------
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//-------------------------------------------------------------------------------
			
			MessageConsumer consumer = session.createConsumer(queue);
			
			connection.start();
			
			TextMessage receivedMessage = (TextMessage) consumer.receive(6000);
			
			System.out.println("Message received : " + receivedMessage);
			
			//---------------------------------------------------------------------------------
			
			Queue expiryQueue = session.createQueue("ExpiryQueue");
			
			MessageConsumer consumerExp = session.createConsumer(expiryQueue);
			
			TextMessage expiredMessage = (TextMessage) consumerExp.receive(1000);
			
			System.out.println("Expired message : " + expiredMessage.getText());
			
			
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
