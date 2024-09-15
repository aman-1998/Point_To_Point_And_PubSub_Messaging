package personal.learning.activemq.jms;

import java.util.Enumeration;

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

public class Producing_More_Than_One_Messages_N3 {
	
public static void main(String[] args) {
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = null;
		Session session = null;
		
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession();
			
			Queue queue = session.createQueue("testQueue");
			
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
