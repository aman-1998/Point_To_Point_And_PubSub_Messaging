package personal.learning.activemq.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class Consuming_Messages_One_By_One_N5 {
	
public static void main(String[] args) {
		
		String brokerUrl = "tcp://localhost:61616";
		ConnectionFactory connectionFactory = null;
		Connection connection = null;
		Session session = null;
		MessageConsumer consumer = null;
		
		try {
			connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
			connection = connectionFactory.createConnection();
			session = connection.createSession();
			
			Queue queue = session.createQueue("testQueue");
			
			consumer = session.createConsumer(queue);
			
			connection.start();
			
			while(true) {
				// Infinite wait
				Message message = consumer.receive();
				System.out.println("Message received : " + message.getBody(String.class));
			}
			
			
		} catch (JMSException ex) {
			try {
				if(session != null) {
					session.close();
				}
				
				if(connection != null) {
					connection.close();
				}
				
				if(consumer != null) {
					consumer.close();
				}
			} catch(JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
