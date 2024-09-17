package personal.learning.activemq.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
/*
 * Producer must be running second because we have created non-durableSubscription
 */
public class Producing_Message_N1 {
	
	public static void main(String[] args) {
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		
		Connection connection = null;
		Session session = null;
		
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession();
			
			Topic topic = session.createTopic("topic2");
			
			MessageProducer producer = session.createProducer(topic);
			
			String msg = "How are you Aman ?";
			TextMessage textMessage = session.createTextMessage(msg);
			producer.send(textMessage);
			System.out.println("Message sent: " + textMessage.getText());
			
			//---------------------------------------------------------------
			
			String msg2 = "How are you Sweta ?";
			TextMessage textMessage1 = session.createTextMessage(msg2);
			producer.send(textMessage1);
			System.out.println("Message sent: " + textMessage1.getText());
			
			
			
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
