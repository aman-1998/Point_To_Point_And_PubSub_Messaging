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

public class OrderHistResProducer2 {
	
	public static void main(String[] args) {
		
		String brokerUrl = "tcp://localhost:61616";
		Connection connection = null;
		Session session = null;
		
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
			connection = connectionFactory.createConnection();
			session = connection.createSession(Session.CLIENT_ACKNOWLEDGE);
			
			Queue orderHistoryReqQueue = session.createQueue("orderHistRequestQueue");
			Queue orderHistoryResQueue = session.createQueue("orderHistResponseQueue");
			
			MessageProducer producer = session.createProducer(orderHistoryReqQueue);
			
			ObjectMessage objectMessage1 = session.createObjectMessage();
			objectMessage1.setJMSMessageID("ID:F12345");
			OrderHistoryReq request1 = new OrderHistoryReq();
			request1.setUsername("start_neha");
			request1.setFromDate("20-01-2024");
			request1.setToDate("25-11-2024");
			objectMessage1.setObject(request1);
			objectMessage1.setJMSReplyTo(orderHistoryResQueue);
			
			ObjectMessage objectMessage2 = session.createObjectMessage();
			objectMessage2.setJMSMessageID("ID:M54321");
			OrderHistoryReq request2 = new OrderHistoryReq();
			request2.setUsername("aman_cool");
			request2.setFromDate("12-03-2024");
			request2.setToDate("20-12-2024");
			objectMessage2.setObject(request2);
			objectMessage2.setJMSReplyTo(orderHistoryResQueue);
			
			ObjectMessage objectMessage3 = session.createObjectMessage();
			objectMessage3.setJMSMessageID("ID:F12345");
			OrderHistoryReq request3 = new OrderHistoryReq();
			request3.setUsername("madhu1998");
			request3.setFromDate("30-06-2024");
			request3.setToDate("20-10-2024");
			objectMessage3.setObject(request3);
			objectMessage3.setJMSReplyTo(orderHistoryResQueue);
			
			ObjectMessage objectMessage4 = session.createObjectMessage();
			objectMessage4.setJMSMessageID("ID:M54321");
			OrderHistoryReq request4 = new OrderHistoryReq();
			request4.setUsername("awsome_ravi");
			request4.setFromDate("21-02-2024");
			request4.setToDate("05-09-2024");
			objectMessage4.setObject(request4);
			objectMessage4.setJMSReplyTo(orderHistoryResQueue);
			
			ObjectMessage objectMessage5 = session.createObjectMessage();
			objectMessage5.setJMSMessageID("ID:M54321");
			OrderHistoryReq request5 = new OrderHistoryReq();
			request5.setUsername("mithila_nagri");
			request5.setFromDate("15-04-2024");
			request5.setToDate("25-12-2024");
			objectMessage5.setObject(request5);
			objectMessage5.setJMSReplyTo(orderHistoryResQueue);
			
			ObjectMessage objectMessage6 = session.createObjectMessage();
			objectMessage6.setJMSMessageID("ID:M54321");
			OrderHistoryReq request6 = new OrderHistoryReq();
			request6.setUsername("imVKohli");
			request6.setFromDate("08-03-2024");
			request6.setToDate("24-11-2024");
			objectMessage6.setObject(request6);
			objectMessage6.setJMSReplyTo(orderHistoryResQueue);
			
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
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			producer.send(objectMessage4);
			System.out.println("Message sent: " + request4);
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			producer.send(objectMessage5);
			System.out.println("Message sent: " + request5);
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			producer.send(objectMessage6);
			System.out.println("Message sent: " + request6);
			
			//---------------------------------------------------------------------------------------
			
			MessageConsumer orderDetailListConsumer = session.createConsumer(orderHistoryResQueue);
			
			orderDetailListConsumer.setMessageListener(message -> {
				
				try {
					ArrayList<OrderDetails> orderDetailsList = message.getBody(ArrayList.class);
					System.out.println("Order details: " + orderDetailsList);
					
					message.acknowledge();
					
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
