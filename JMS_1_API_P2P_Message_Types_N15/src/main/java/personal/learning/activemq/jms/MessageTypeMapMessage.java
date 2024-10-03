package personal.learning.activemq.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class MessageTypeMapMessage {
	
	public static void main(String[] args) {
		
		String brokerUrl = "tcp://localhost:61616";
		Connection connection = null;
		Session session = null;
		
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
			connection = connectionFactory.createConnection();
			session = connection.createSession();
			
			Queue queue = session.createQueue("queue7");
			
			MessageProducer producer = session.createProducer(queue);
			
			MapMessage mapMessage = session.createMapMessage();
			mapMessage.setBoolean("mapBooleanProperty", true);
			mapMessage.setChar("characterProperty", 'A');
			mapMessage.setDouble("doubleProperty", 331.48);
			
			producer.send(mapMessage);
			
			//--------------------------------------------------------------------------------------
			
			MessageConsumer consumer = session.createConsumer(queue);
			
			connection.start();
			
			MapMessage receivedMessage = (MapMessage) consumer.receive();
			
			System.out.println(receivedMessage.getBoolean("mapBooleanProperty"));
			System.out.println(receivedMessage.getChar("characterProperty"));
			System.out.println(receivedMessage.getDouble("doubleProperty"));
			
			
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
