package personal.learning.activemq.jms.consumer.security;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import personal.learning.activemq.jms.listener.SecurityAppListener;
import personal.learning.activemq.jms.model.Employee;

public class SecurityAppConsumer2 {
	
	public static void main(String[] args) {
		
		String brokerUrl = "tcp://localhost:61616";
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		Connection connection = null;
		Session session = null;
		
		try {
			connection = connectionFactory.createConnection();
			//connection.setClientID("SecuritySubscriberID");
			
			session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			
			Queue queue = session.createQueue("SharedTestQueueSecurity");
			
			MessageConsumer consumer = session.createConsumer(queue);
			SecurityAppListener securityAppListener = new SecurityAppListener(session, "SecurityAppConsumer2");
			consumer.setMessageListener(securityAppListener);
			
			connection.start();
			
			System.out.println("SecurityAppConsumer2 subscribed to topic");
			
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
