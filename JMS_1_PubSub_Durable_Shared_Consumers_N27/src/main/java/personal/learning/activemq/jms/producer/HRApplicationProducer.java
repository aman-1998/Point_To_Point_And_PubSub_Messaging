package personal.learning.activemq.jms.producer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import personal.learning.activemq.jms.model.Employee;
/*
 * Producer must be running second because we have created non-durableSubscription
 */
public class HRApplicationProducer {
	
	public static void main(String[] args) {
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = null;
		Session session = null;
		
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession();
			
			Topic topic = session.createTopic("OnboardingTopic1");
			Queue replyQueue = session.createQueue("OnboardingStatusQueue");
			
			MessageProducer producer = session.createProducer(topic);
			
			Employee employee1 = new Employee();
			employee1.setId("B092660");
			employee1.setFirstName("Navya");
			employee1.setLastName("Tyagi");
			employee1.setDesignation("Associate Software Engineer");
			employee1.setEmail("navya.tyagi2002@gmail.com");
			employee1.setPhone("+91-8576883459");
			ObjectMessage objectMessage1 = session.createObjectMessage();
			objectMessage1.setObject(employee1);
			objectMessage1.setJMSReplyTo(replyQueue);
			
			
			Employee employee2 = new Employee();
			employee2.setId("B077336");
			employee2.setFirstName("Shyam");
			employee2.setLastName("Mishra");
			employee2.setDesignation("Software Engineer");
			employee2.setEmail("shyam.mishra1998@gmail.com");
			employee2.setPhone("+91-8863916394");
			ObjectMessage objectMessage2 = session.createObjectMessage();
			objectMessage2.setObject(employee2);
			objectMessage2.setJMSReplyTo(replyQueue);
			
			
			Employee employee3 = new Employee();
			employee3.setId("B099832");
			employee3.setFirstName("Rahul");
			employee3.setLastName("Bhadauria");
			employee3.setDesignation("Associate Software Engineer");
			employee3.setEmail("rahul.bhadauria2001@gmail.com");
			employee3.setPhone("+91-9720752198");
			ObjectMessage objectMessage3 = session.createObjectMessage();
			objectMessage3.setObject(employee3);
			objectMessage3.setJMSReplyTo(replyQueue);
			
			
			Employee employee4 = new Employee();
			employee4.setId("B040832");
			employee4.setFirstName("Vaishnavi");
			employee4.setLastName("Dhoniyal");
			employee4.setDesignation("Senior Software Engineer");
			employee4.setEmail("vaishnavi.dhoniyal1996@gmail.com");
			employee4.setPhone("+91-9726189320");
			ObjectMessage objectMessage4 = session.createObjectMessage();
			objectMessage4.setObject(employee4);
			objectMessage4.setJMSReplyTo(replyQueue);
			
			
			producer.send(objectMessage1);
			System.out.println("Message sent: " + employee1);
			producer.send(objectMessage2);
			System.out.println("Message sent: " + employee2);
			producer.send(objectMessage3);
			System.out.println("Message sent: " + employee3);
			producer.send(objectMessage4);
			System.out.println("Message sent: " + employee4);
			
		} catch(JMSException e) {
			e.printStackTrace();
		} finally {
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
