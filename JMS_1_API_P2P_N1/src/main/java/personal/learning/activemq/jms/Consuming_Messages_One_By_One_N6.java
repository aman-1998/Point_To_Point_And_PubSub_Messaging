package personal.learning.activemq.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class Consuming_Messages_One_By_One_N6 {
	
	public static void main(String[] args) {
		
		String brokerUrl = "tcp://localhost:61616";
		ConnectionFactory connectionFactory = null;
		Connection connection = null;
		Session session = null;
		MessageConsumer consumer = null;
		
		try {
			connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
			connection = connectionFactory.createConnection();
			session = connection.createSession();
			
			Queue queue = session.createQueue("testQueue");
			
			consumer = session.createConsumer(queue);
			
			connection.start();
			
			int flag = 1;
			while(true) {
				/*
				 * If there is no messages in the queue then it will wait 
				 * for 1 sec for the arrival of message.
				 */
				Message message = consumer.receive(1000);
				
				if(message == null) {
					break;
				}
				
				System.out.println("Message received : " + message.getBody(String.class));
				flag++;
			}
			
			if(flag == 1) {
				System.out.println(queue.getQueueName() + " queue is empty");
			} else {
				System.out.println(queue.getQueueName() + " doesn't have any message left");
			}
			
		} catch (JMSException ex) {
			try {
				if(session != null) {
					session.close();
				}
				
				if(connection != null) {
					connection.close();
				}
				
				if(consumer != null) {
					consumer.close();
				}
			} catch(JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
