package personal.learning.activemq.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class Producing_And_Consuming_N1 {
	public static void main(String[] args) {
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = null;
		Session session = null;
		
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession();
			
			Topic topic = session.createTopic("topic1");
			
			MessageProducer producer = session.createProducer(topic);
			
			MessageConsumer consumer1 = session.createConsumer(topic); // Consumer1 subscribed to topic
			MessageConsumer consumer2 = session.createConsumer(topic); // Consumer2 subscribed to topic
			MessageConsumer consumer3 = session.createConsumer(topic); // Consumer3 subscribed to topic
			MessageConsumer consumer4 = session.createConsumer(topic); // Consumer4 subscribed to topic
			
			//------------------------------------------------------------------------------------------
			
			String msg = "Hi Aman! how are you ?";
			TextMessage textMessage = session.createTextMessage(msg);
			producer.send(textMessage);
			System.out.println("Message sent: " + textMessage.getText());
			
			//------------------------------------------------------------------------------------------
			
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//------------------------------------------------------------------------------------------
			
			connection.start(); // Topic can now push message to the consumers
			
			Message message1 = consumer1.receive();
			System.out.println("Message received by consumer1: " + message1.getBody(String.class));
			
			Message message2 = consumer2.receive();
			System.out.println("Message received by consumer2: " + ((TextMessage) message2).getText());
			
			Message message3 = consumer3.receive();
			System.out.println("Message received by consumer3: " + message3.getBody(String.class));
			
			Message message4 = consumer4.receive();
			System.out.println("Message received by consumer4: " + ((TextMessage) message4).getText());
			
			
		} catch(JMSException e) {
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
