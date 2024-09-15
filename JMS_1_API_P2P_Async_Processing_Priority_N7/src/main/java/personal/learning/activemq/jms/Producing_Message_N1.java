package personal.learning.activemq.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class Producing_Message_N1 {
	
	public static void main(String[] args) {
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = null;
		Session session = null;
		
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession();
			
			Queue queue = session.createQueue("queue4");
			
			MessageProducer producer = session.createProducer(queue);
			
			String[] messages = new String[5];
			messages[0] = "My name is Aman Kumar Mishra";
			messages[1] = "My name is Nitu Rathore";
			messages[2] = "My name is Raghav Jha";
			messages[3] = "My name is Satya Nadela";
			messages[4] = "My name is Sraddha Kishore";
			
			producer.setPriority(5);
			TextMessage textMessage0 = session.createTextMessage(messages[0]);
			producer.send(textMessage0);
			
			producer.setPriority(7);
			TextMessage textMessage1 = session.createTextMessage(messages[1]);
			producer.send(textMessage1);
			TextMessage textMessage2 = session.createTextMessage(messages[2]);
			producer.send(textMessage2);
			
			producer.setPriority(9);
			TextMessage textMessage3 = session.createTextMessage(messages[3]);
			producer.send(textMessage3);
			
			producer.setPriority(1);
			TextMessage textMessage4 = session.createTextMessage(messages[4]);
			producer.send(textMessage4);
			
			
		} catch(JMSException e) {
			e.printStackTrace();
		}
	}
}
