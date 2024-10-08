package personal.learning.activemq.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
/*
 * Run Consumer second
 */
public class Consuming_Message_N2 {

	public static void main(String[] args) {
		ConnectionFactory connectionFactoty = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = null;
		Session session = null;
		try {
			connection = connectionFactoty.createConnection();
			session = connection.createSession();

			Queue queue = session.createQueue("myQueue");

			MessageConsumer consumer = session.createConsumer(queue);
			
			connection.start(); // Now consumer can poll from queue
			
			TextMessage receivedMessage = (TextMessage) consumer.receive(); // Infinite wait
			System.out.println("Received message: " + receivedMessage.getText());

		} catch (JMSException e) {
			e.printStackTrace();
		} finally {

			try {
				if (session != null) {
					session.close();
				}

				if (connection != null) {
					connection.close();
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}

		}
	}
}
