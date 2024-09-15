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

public class Browsing_N2 {
	
	public static void main(String[] args) {
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = null;
		Session session = null;
		
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession();
			
			Queue queue = session.createQueue("queue2");
			
			QueueBrowser queueBrowser = session.createBrowser(queue);
			
			Enumeration<?> messagesEnum = queueBrowser.getEnumeration();
			
			// Using Iterator
			Iterator<?> iterator = messagesEnum.asIterator();
			
			if(!iterator.hasNext()) {
				System.out.println("No messages are available in queue : " + queue.getQueueName());
			} else {
				while(iterator.hasNext()) {
					Message message = (Message) iterator.next();
					if(message instanceof TextMessage) {
						TextMessage textMessage = (TextMessage) message;
						System.out.println("Message in queue : " + textMessage.getText());
					} else {
						System.out.println("Non-text message : " + message);
					}
				}
			}
			
			
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
