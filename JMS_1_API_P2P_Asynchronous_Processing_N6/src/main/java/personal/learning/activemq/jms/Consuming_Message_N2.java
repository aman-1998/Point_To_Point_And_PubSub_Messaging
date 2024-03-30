package personal.learning.activemq.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
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
			
			Queue queue = session.createQueue("queue3");
			
			connection.start();
			
			MessageConsumer consumer = session.createConsumer(queue);
			MessageListener messageProcessor = new MessageProcessor();
			consumer.setMessageListener(messageProcessor);
			
			//Keep main thread alive to continue message consumption
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

class MessageProcessor implements MessageListener {

	public void onMessage(Message message) {
		
		try {
			System.out.println("Message received: " + message.getBody(String.class));
			
			//Processing taking 10s
			Thread.sleep(5000);
			
			System.out.println("Processing done :-)");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
}
