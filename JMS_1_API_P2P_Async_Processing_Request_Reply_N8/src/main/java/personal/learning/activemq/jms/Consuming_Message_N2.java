package personal.learning.activemq.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class Consuming_Message_N2 {
	
	public static void main(String[] args) {
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = null;
		Session session = null;
		
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession();
			
			Queue queue = session.createQueue("queue4");
			
			MessageConsumer consumer = session.createConsumer(queue);
			consumer.setMessageListener(message -> {
				try {
					System.out.println("Message received = " +  message.getBody(String.class));
					try {
						System.out.println("Processing...");
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Message processed :-)");
				} catch(JMSException e) {
					e.printStackTrace();
				}
			});
			
			connection.start();
			
			try {
				Thread.sleep(1200000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		} catch(JMSException e) {
			e.printStackTrace();
		}
	}
}
