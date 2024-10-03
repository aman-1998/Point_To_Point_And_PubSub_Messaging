package personal.learning.activemq.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.StreamMessage;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class MessageTypeStreamMessage {
	
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
			
			StreamMessage streamMessage = session.createStreamMessage();
			streamMessage.writeBoolean(true);
			streamMessage.writeFloat(2.78F);
			streamMessage.setIntProperty("intProperty", 144);
			
			messageProducer.send(streamMessage);
			
			//---------------------------------------------------------------------------------------------
			
			MessageConsumer consumer = session.createConsumer(queue);
			
			connection.start();
			
			StreamMessage receivedMessage = (StreamMessage) consumer.receive();
			
			System.out.println(receivedMessage.readBoolean());
			System.out.println(receivedMessage.readFloat());
			System.out.println(receivedMessage.getIntProperty("intProperty"));
			
			
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
