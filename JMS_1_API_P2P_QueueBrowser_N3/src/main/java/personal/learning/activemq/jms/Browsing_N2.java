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
			
			Enumeration<TextMessage> messagesEnum = queueBrowser.getEnumeration();
			
			// Using Enumeration
			while(messagesEnum.hasMoreElements()) {
				TextMessage msg = messagesEnum.nextElement();
                System.out.println("Messages in queue: " + msg.getText());
            }
			
			System.out.println("-------------");
			// Using Iterator
			Iterator<TextMessage> iterator = messagesEnum.asIterator();
			
			while(iterator.hasNext()) {
				TextMessage msg = iterator.next();
                System.out.println("Messages in queue: " + msg.getText());
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
