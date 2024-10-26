package personal.learning.activemq.jms.consumer.wellness;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import personal.learning.activemq.jms.listener.WellnessAppListener;
import personal.learning.activemq.jms.model.Employee;

public class WellnessAppConsumer1 {
	
	public static void main(String[] args) {
		
		String brokerUrl = "tcp://localhost:61616";
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		Connection connection = null;
		Session session = null;
		
		try {
			connection = connectionFactory.createConnection();
			//connection.setClientID("WellnessSubscriberID");
			
			session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			
			Topic topic = session.createTopic("OnboardingTopic1");
			
			MessageConsumer consumer = session.createSharedDurableConsumer(topic, "subscriptionWellnessApp");
			WellnessAppListener wellnessAppListener = new WellnessAppListener(session, "WellnessAppConsumer1");
			consumer.setMessageListener(wellnessAppListener);
			
			connection.start();
			
			System.out.println("WellnessAppConsumer1 subscribed to topic");
			
			try {
				Thread.sleep(1200000);
			} catch (InterruptedException e) {
				e.printStackTrace();
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
