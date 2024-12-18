package personal.learning.activemq.jms;

import java.util.Enumeration;
import java.util.Iterator;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
/*
 * ChatGPT:
 * 
 * In your code, you're attempting to consume messages from the queue using both a MessageConsumer and a QueueBrowser. However, this approach is not correct because you're mixing two different ways of consuming messages, which may lead to unexpected behavior.
 *
 * Here's what's happening in your code:
 *
 * You create a MessageConsumer to consume messages from the queue using session.createConsumer(queue).
 * You create a QueueBrowser to browse messages in the queue using session.createBrowser(queue).
 * You retrieve an Enumeration of messages from the QueueBrowser.
 * You convert the Enumeration to an Iterator and iterate over it.
 * Inside the loop, you attempt to consume messages using consumer.receive().
 * The issue here is that you're mixing consumption methods. You should either consume messages using the MessageConsumer or browse messages using the QueueBrowser, but not both simultaneously in the same loop.
 *
 * If you want to consume messages, you should remove the QueueBrowser part and consume messages using only the MessageConsumer.
 *
 * If you want to browse messages without consuming them, you should remove the MessageConsumer part and browse messages using only the QueueBrowser.
 *
 * Decide which approach fits your requirements, and modify your code accordingly.
 */
public class Consuming_And_Browsing_N3 {
	
	public static void main(String[] args) {
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = null;
		Session session = null;
		
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession();
			
			Queue queue = session.createQueue("queue2");
			
			MessageConsumer consumer = session.createConsumer(queue);
			
			QueueBrowser queueBrowser = session.createBrowser(queue);
			
			Enumeration<TextMessage> messagesEnum = queueBrowser.getEnumeration();
			Iterator<TextMessage> iterator = messagesEnum.asIterator();
			
			while(iterator.hasNext()) {
				TextMessage msg = (TextMessage) consumer.receive();
                System.out.println("Messages in queue: " + msg.getText());
            }
			/*
			 * ChatGPT:
			 * 
			 * In your code, you're attempting to consume messages from the queue using both a MessageConsumer and a QueueBrowser. However, this approach is not correct because you're mixing two different ways of consuming messages, which may lead to unexpected behavior.
			 *
			 * Here's what's happening in your code:
			 *
			 * You create a MessageConsumer to consume messages from the queue using session.createConsumer(queue).
			 * You create a QueueBrowser to browse messages in the queue using session.createBrowser(queue).
			 * You retrieve an Enumeration of messages from the QueueBrowser.
			 * You convert the Enumeration to an Iterator and iterate over it.
			 * Inside the loop, you attempt to consume messages using consumer.receive().
			 * The issue here is that you're mixing consumption methods. You should either consume messages using the MessageConsumer or browse messages using the QueueBrowser, but not both simultaneously in the same loop.
			 *
			 * If you want to consume messages, you should remove the QueueBrowser part and consume messages using only the MessageConsumer.
			 *
			 * If you want to browse messages without consuming them, you should remove the MessageConsumer part and browse messages using only the QueueBrowser.
			 *
			 * Decide which approach fits your requirements, and modify your code accordingly.
			 */
			
			
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
