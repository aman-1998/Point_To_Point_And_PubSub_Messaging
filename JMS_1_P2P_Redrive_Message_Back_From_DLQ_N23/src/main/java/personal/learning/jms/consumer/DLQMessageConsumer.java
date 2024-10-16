package personal.learning.jms.consumer;

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

import personal.learning.jms.model.OrderDetails;
import personal.learning.jms.model.OrderHistoryReq;
import personal.learning.jms.service.OrderDetailsService;

public class DLQMessageConsumer {
	
	public static void main(String[] args) {
		
		String brokerUrl = "tcp://localhost:61616";
		Connection connection = null;
		Session sessionOut = null;
		
		try {
			
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
			connection = connectionFactory.createConnection();
			final Session session = connection.createSession(Session.AUTO_ACKNOWLEDGE);
			
			Queue deadLetterQueue = session.createQueue("DLQ");
			
			MessageConsumer deadMessageConsumer = session.createConsumer(deadLetterQueue);
			
			deadMessageConsumer.setMessageListener(message -> {
				try {
					OrderHistoryReq requestMessage = message.getBody(OrderHistoryReq.class);
					
					System.out.println("Message received from DLQ: " + requestMessage);
					
					String fromDate = requestMessage.getFromDate();
					String toDate = requestMessage.getToDate();
					String username = requestMessage.getUsername();
					
					ArrayList<OrderDetails> orderDetailsList = new OrderDetailsService().getOrderDetails(username, fromDate, toDate);
					
					MessageProducer orderDetailProducer = session.createProducer(message.getJMSReplyTo());
					
					ObjectMessage orderDetailsListMsg = session.createObjectMessage();
					orderDetailsListMsg.setObject(orderDetailsList);
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					orderDetailProducer.send(orderDetailsListMsg);
					
				} catch(JMSException ex) {
					ex.printStackTrace();
				}
			});
			
			connection.start();
			
			System.out.println("DLQ Consumer is waiting for message...");
			
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
				
				if(sessionOut != null) {
					sessionOut.close();
				}
				
			} catch(JMSException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
}
