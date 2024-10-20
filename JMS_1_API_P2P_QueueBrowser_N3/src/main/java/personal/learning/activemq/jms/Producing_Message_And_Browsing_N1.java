package personal.learning.activemq.jms;

import java.util.Enumeration;
import java.util.Iterator;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class Producing_Message_And_Browsing_N1 {
	public static void main(String[] args) {
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = null;
		Session session = null;
		
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession();
			
			Queue queue = session.createQueue("queue2");
			
			MessageProducer producer = session.createProducer(queue);
			
			//-----------------------------------------------------------
			
			String msg1 = "My name is Aman";
			TextMessage textMessage1 = session.createTextMessage(msg1);
			
			producer.send(textMessage1);
			
			System.out.println("Message sent: " + textMessage1.getText());
			
			//------------------------------------------------------------
			
			String msg2 = "My name is Rudra";
			TextMessage textMessage2 = session.createTextMessage(msg2);
			
			producer.send(textMessage2);
			
			System.out.println("Message sent: " + textMessage2.getText());
			
			//-------------------------------------------------------------
			
			String msg3 = "My name is Akshay";
			TextMessage textMessage3 = session.createTextMessage(msg3);
			
			producer.send(textMessage3);
			
			System.out.println("Message sent: " + textMessage3.getText());
			
			//--------------------------------------------------------------
			
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("-------------------------------------");
			QueueBrowser queueBrowser = session.createBrowser(queue);
			
			Enumeration<?> messagesEnum = queueBrowser.getEnumeration();
			
			if(!messagesEnum.hasMoreElements()) {
				System.out.println("No messages are available in queue : " + queue.getQueueName());
			} else {
				while(messagesEnum.hasMoreElements()) {
					Message message = (Message) messagesEnum.nextElement();
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
