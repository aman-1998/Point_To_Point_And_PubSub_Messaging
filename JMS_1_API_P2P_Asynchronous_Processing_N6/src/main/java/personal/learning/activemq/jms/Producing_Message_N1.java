package personal.learning.activemq.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class Producing_Message_N1 {
	
	public static void main(String[] args) {
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = null;
		Session session = null;
		
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession();
			
			Queue queue = session.createQueue("queue3");
			
			MessageProducer producer = session.createProducer(queue);
			
			int id = 23;
			String name = "Aman";
			String school ="KHEMS";
			String message = "{ \"id\": " + id + ", \"name\": \"" + name + "\", \"school\": \"" + school + "\" }";
			TextMessage textMessage = session.createTextMessage(message);
			
			producer.send(textMessage);
			System.out.println("Message sent: " + textMessage.getText());
			
		} catch(JMSException e) {
			e.printStackTrace();
		}
	}
}
