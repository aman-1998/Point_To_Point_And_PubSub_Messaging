package personal.learning.jms.producer;

import java.util.ArrayList;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import personal.learning.jms.model.OrderHistoryReq;
import personal.learning.jms.model.OrderDetails;

public class OrderHistResProducer {
	
	public static void main(String[] args) {
		
		String brokerUrl = "tcp://localhost:61616";
		Connection connection = null;
		Session session = null;
		
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
			connection = connectionFactory.createConnection();
			session = connection.createSession();
			
			Queue orderHistoryReqQueue = session.createQueue("orderHistoryReqQueue");
			Queue orderHistoryResQueue = session.createQueue("orderHistoryResQueue");
			
			MessageProducer producer = session.createProducer(orderHistoryReqQueue);
			
			ObjectMessage objectMessage1 = session.createObjectMessage();
			OrderHistoryReq request1 = new OrderHistoryReq();
			request1.setUsername("start_neha");
			request1.setFromDate("20-01-2024");
			request1.setToDate("25-11-2024");
			objectMessage1.setObject(request1);
			objectMessage1.setJMSReplyTo(orderHistoryResQueue);
			
			ObjectMessage objectMessage2 = session.createObjectMessage();
			OrderHistoryReq request2 = new OrderHistoryReq();
			request2.setUsername("aman_cool");
			request2.setFromDate("12-03-2024");
			request2.setToDate("20-12-2024");
			objectMessage2.setObject(request2);
			objectMessage2.setJMSReplyTo(orderHistoryResQueue);
			
			ObjectMessage objectMessage3 = session.createObjectMessage();
			OrderHistoryReq request3 = new OrderHistoryReq();
			request3.setUsername("madhu1998");
			request3.setFromDate("30-06-2024");
			request3.setToDate("20-10-2024");
			objectMessage3.setObject(request3);
			objectMessage3.setJMSReplyTo(orderHistoryResQueue);
			
			producer.send(objectMessage1);
			System.out.println("Message sent: " + request1);
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			producer.send(objectMessage2);
			System.out.println("Message sent: " + request2);
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			producer.send(objectMessage3);
			System.out.println("Message sent: " + request3);
			
			//---------------------------------------------------------------------------------------
			
			MessageConsumer orderDetailListConsumer = session.createConsumer(orderHistoryResQueue);
			orderDetailListConsumer.setMessageListener(message -> {
				
				try {
					ArrayList<OrderDetails> orderDetailsList = message.getBody(ArrayList.class);
					System.out.println("Order details: " + orderDetailsList);
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			});
			
			connection.start();
			
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
