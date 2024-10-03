package personal.learning.activemq.jms;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class MessageTypeBytesMessage {
	
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
			
			BytesMessage bytesMessage = session.createBytesMessage();
			bytesMessage.writeUTF("Aman Kumar Mishra");
			bytesMessage.writeLong(123L);
			
			messageProducer.send(bytesMessage);
			
			System.out.println("Message sent");
			
			//--------------------------------------------------------------------------------------
			
			MessageConsumer consumer = session.createConsumer(queue);
			
			connection.start();
			
			BytesMessage receivedMessage = (BytesMessage) consumer.receive();
			
			System.out.println("String : " + receivedMessage.readUTF());
			System.out.println("Long : " + receivedMessage.readLong());
			
			
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
