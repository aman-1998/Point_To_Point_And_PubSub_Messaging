package personal.learning.activemq.jms.consumer.wellness;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import personal.learning.activemq.jms.model.Employee;

public class WellnessAppConsumer2 {
	
	public static void main(String[] args) {
		
		String brokerUrl = "tcp://localhost:61616";
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		Connection connection = null;
		Session session = null;
		
		try {
			connection = connectionFactory.createConnection();
			
			session = connection.createSession(Session.AUTO_ACKNOWLEDGE);
			
			Topic topic = session.createTopic("OnboardingTopic");
			
			MessageConsumer consumer = session.createSharedConsumer(topic, "sharedConsumerForWellnessApp");
			
			connection.start();
			
			System.out.println("WellnessAppConsumer2 subscribed to topic");
			
			while(true) {
				Message message = consumer.receive();
				System.out.println("Message received by WellnessAppConsumer2: " + message.getBody(Employee.class));
			}
			
		} catch(JMSException ex) {
			
			System.out.println("Exception occured");
			ex.printStackTrace();
			
			try {
				if(session == null) {
					session.close();
				}
				
				if(connection == null) {
					connection.close();
				}
			} catch(JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
