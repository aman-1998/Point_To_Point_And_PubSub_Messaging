package personal.learning.activemq.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
/*
 * Run Producer first
 */
public class Producing_Message_N1 {
	public static void main(String[] args) {
		ConnectionFactory connectionFactoty = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = null;
		Session session = null;
		try {
			connection = connectionFactoty.createConnection();
			session = connection.createSession();

			Queue queue = session.createQueue("myQueue");

			MessageProducer producer = session.createProducer(queue);

			String message = "Hello! My name is Sumit";
			TextMessage textMessage = session.createTextMessage(message);

			producer.send(textMessage);
			System.out.println("Message sent: " + message);

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
