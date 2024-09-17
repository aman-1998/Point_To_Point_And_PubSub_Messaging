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
 * 
 * In a JMS topic (like in ActiveMQ), messages are treated differently than in queues. 
 * In a topic, messages are broadcast to all active subscribers, but they are not deleted 
 * immediately after being delivered. Here’s what happens:
 *
 * Non-Durable Subscribers:
 * =========================
 * For non-durable subscribers, the message is delivered only if the subscriber is actively listening 
 * when the message is published.
 *
 * Once all active non-durable subscribers receive the message, it is not stored anymore, and no new 
 * subscribers can receive that message. Non-durable subscribers only receive messages while they are 
 * connected and actively subscribed to the topic. No Message Retention: After all active subscribers have 
 * received the message, it is effectively "gone" from the broker’s perspective. The message is not stored 
 * for future subscribers.
 *
 *
 * Durable Subscribers:
 * =====================
 * For durable subscribers, the message is stored until all durable subscribers have received it, even if 
 * they are not currently connected. When a durable subscriber disconnects, the broker retains the messages 
 * sent to the topic while the subscriber was offline. When the durable subscriber reconnects, it will receive 
 * all messages sent while it was offline.
 * 
 * 
 * Messages are retained until acknowledged: 
 * ==========================================
 * Once all durable subscribers have successfully received the message, the message is removed from the 
 * topic's storage. If a durable subscriber does not acknowledge the message (for example, due to a disconnection), 
 * the broker retains the message for that subscriber until it reconnects and acknowledges the message.
 * 
 * 
 * Key Differences from Queues:
 * =============================
 * In a queue, a message is delivered to only one consumer, and once delivered, the message is removed from 
 * the queue. 
 * In a topic, the message is broadcast to all subscribers, and for durable subscribers, the message 
 * is stored until all subscribers have received and acknowledged it.
 * 
 * 
 * Message Retention in Topics:
 * =============================
 * Non-durable subscribers: Messages are not stored after delivery to active subscribers.
 * Durable subscribers: Messages are stored until each durable subscriber receives and acknowledges the message.
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
			
			
			System.out.println("\n-----------------------------------------------------------------------------------");
			
			
			Message message4 = consumer1.receiveNoWait();
			System.out.println("Message received by consumer1: " + message4.getBody(String.class));
			
			Message message5 = consumer2.receiveNoWait();
			System.out.println("Message received by consumer2: " + message5.getBody(String.class));
			
			Message message6 = consumer3.receiveNoWait();
			System.out.println("Message received by consumer3: " + message6.getBody(String.class));
			
			
			// Message is deleted from Topic once all the subscribers received message
			System.out.println("\n-----------------------------------------------------------------------------------");
			
			
			Message message7 = consumer1.receiveNoWait();
			System.out.println("Message received by consumer1: " + message7.getBody(String.class));
			
			Message message8 = consumer2.receiveNoWait();
			System.out.println("Message received by consumer2: " + message8.getBody(String.class));
			
			Message message9 = consumer3.receiveNoWait();
			System.out.println("Message received by consumer3: " + message9.getBody(String.class));
			
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
