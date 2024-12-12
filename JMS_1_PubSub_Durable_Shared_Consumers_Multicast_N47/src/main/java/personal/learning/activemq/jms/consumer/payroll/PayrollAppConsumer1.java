package personal.learning.activemq.jms.consumer.payroll;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import personal.learning.activemq.jms.listener.PayrollAppListener;

public class PayrollAppConsumer1 {
	
	public static void main(String[] args) {
		
		String brokerUrl = "tcp://localhost:61616";
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		
		Connection connection = null;
		Session session = null;
		
		try {
			connection = connectionFactory.createConnection();
			//connection.setClientID("PayrollSubscriberID");
			
			session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			
			Queue queue = session.createQueue("SharedTestQueuePayroll");
			
			MessageConsumer consumer = session.createConsumer(queue);
			PayrollAppListener payrollAppListener = new PayrollAppListener(session, "PayrollAppConsumer1");
			consumer.setMessageListener(payrollAppListener);
			
			connection.start();
			
			System.out.println("PayrollAppConsumer1 subscribed to topic");
			
			try {
				Thread.sleep(1200000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		} catch(JMSException ex) {
			
			System.out.println("Exception occured");
			ex.printStackTrace();
			
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
