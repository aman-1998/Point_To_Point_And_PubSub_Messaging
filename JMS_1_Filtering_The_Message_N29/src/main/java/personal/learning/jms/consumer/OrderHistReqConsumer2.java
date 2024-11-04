package personal.learning.jms.consumer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import personal.learning.jms.listener.OrderHistReqMessageListener;

/*
Filtering can be applied to the following JMS headers:
-------------------------------------------------------
JMSCorrelationID
JMSDeliveryMode
JMSDestination
JMSExpiration
JMSMessageID
JMSType
JMSPriority
JMSRedelivered

Additionally, message properties are very useful for more complex filtering. 
Selectors follow SQL92 syntax and must be specified on the consumer side.

Filtering based on JMSMessageID might not work as expected because JMSMessageID 
is automatically assigned by the JMS provider only after the message has been sent. 
When you set a JMSMessageID manually before sending, it might not be recognized 
correctly by the broker.

*/

public class OrderHistReqConsumer2 {
	
	public static void main(String[] args) {
		
		String brokerUrl = "tcp://localhost:61616";
		Connection connection = null;
		Session session = null;
		
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
			
			connection = connectionFactory.createConnection();
			
			session = connection.createSession(true, Session.SESSION_TRANSACTED);
			
			Queue orderHistoryReqQueue = session.createQueue("orderHistRequestQueue");
			
			MessageConsumer consumer = session.createConsumer(orderHistoryReqQueue, "JMSMessageID = 'ID:F12345'");
			
			OrderHistReqMessageListener messageListener = new OrderHistReqMessageListener(session);
			
			consumer.setMessageListener(messageListener);
			
			connection.start();
			
			System.out.println("Consumer2  waiting for message...");
			
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
