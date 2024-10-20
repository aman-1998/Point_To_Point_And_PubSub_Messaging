package personal.learning.activemq.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class Producing_And_Consuming_N1 {
	public static void main(String[] args) {
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		
		Connection connection = null;
		Session session = null;
		
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession();
			
			Queue queue = session.createQueue("queue1");
			
			MessageProducer producer = session.createProducer(queue);
			
			String msg = "Focus on your goal";
			TextMessage textMessage = session.createTextMessage(msg);
			
			producer.send(textMessage);
			System.out.println("Message sent: " + textMessage.getText());
			
			//----------------------------------------------------------------------
			
			MessageConsumer consumer = session.createConsumer(queue);
			connection.start(); // Consumers can now poll from the queue
			
			Message message = consumer.receiveNoWait();
			System.out.println("Message received: " + message.getBody(String.class));
			
			
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(session != null) {
					session.close();
				}
				
				if(connection != null) {
					connection.close();
				}
			} catch(JMSException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
