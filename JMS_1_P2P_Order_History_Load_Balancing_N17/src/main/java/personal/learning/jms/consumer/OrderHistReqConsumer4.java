package personal.learning.jms.consumer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import personal.learning.jms.listener.OrderHistReqMessageListener;

public class OrderHistReqConsumer4 {
	
	public static void main(String[] args) {
		
		String brokerUrl = "tcp://localhost:61616";
		Connection connection = null;
		Session session = null;
		
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
			connection = connectionFactory.createConnection();
			session = connection.createSession();
			
			Queue orderHistoryReqQueue = session.createQueue("orderHistoryReqQueue");
			
			MessageConsumer consumer = session.createConsumer(orderHistoryReqQueue);
			
			OrderHistReqMessageListener messageListener = new OrderHistReqMessageListener(session);
			
			consumer.setMessageListener(messageListener);
			
			connection.start();
			
			System.out.println("Consumer4  waiting for message...");
			
			try {
				Thread.sleep(1200000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		} catch(JMSException ex) {
			ex.printStackTrace();
		} finally {
			try {
				
				if(connection != null) {
					connection.close();
				}
				
				if(session != null) {
					session.close();
				}
				
			} catch(JMSException ex) {
				ex.printStackTrace();
			}
		}
	}
}
