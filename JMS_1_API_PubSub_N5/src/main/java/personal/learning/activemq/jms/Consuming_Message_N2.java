package personal.learning.activemq.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
/*
 * Consumers must be running first because we have created non-durableSubscription
 * 
 * In ActiveMQ Artemis, topics are not durable by default. This means that if a 
 * subscriber is not active when a message is published to a topic, it will not 
 * receive that message when it becomes active again. Messages sent to non-durable 
 * topics are considered transient and are not stored persistently on the broker.

 * To make a topic durable, you need to create a durable subscription. A durable 
 * subscription allows a subscriber to receive messages sent to a topic even if 
 * it is not actively connected at the time the message is published.
 * 
 */
public class Consuming_Message_N2 {
	
	public static void main(String[] args) {
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = null;
		Session session = null;
		
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession();
			
			Topic topic = session.createTopic("topic2");
			
			MessageConsumer consumer1 = session.createConsumer(topic);
			MessageConsumer consumer2 = session.createConsumer(topic);
			MessageConsumer consumer3 = session.createConsumer(topic);
			
			connection.start(); // Topic can now push message to the consumers i.e., consumers are ready to receive
			
			Message message1 = consumer1.receive();
			System.out.println("Message received by consumer1: " + message1.getBody(String.class));
			
			Message message2 = consumer2.receive();
			System.out.println("Message received by consumer2: " + message2.getBody(String.class));
			
			Message message3 = consumer3.receive();
			System.out.println("Message received by consumer3: " + message3.getBody(String.class));
			
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		} catch(JMSException e) {
			e.printStackTrace();
		}
	
	}
}
