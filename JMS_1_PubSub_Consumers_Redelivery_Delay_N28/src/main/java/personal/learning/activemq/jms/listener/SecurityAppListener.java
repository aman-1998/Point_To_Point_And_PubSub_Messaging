package personal.learning.activemq.jms.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;

import personal.learning.activemq.jms.model.Employee;
import personal.learning.activemq.jms.service.SecurityService;

public class SecurityAppListener implements MessageListener {
	
	private Session session;
	
	private String consumerName;
	
	private int exceptionFlag = 0;
	
	
	public SecurityAppListener(Session session, String consumerName) {
		this.session = session;
		this.consumerName = consumerName;
	}

	public void onMessage(Message message) {

		try {
			Employee employee = message.getBody(Employee.class);
			
			System.out.println("Message received by " + consumerName + ": " + employee);
			
			if(exceptionFlag == 1) {
				System.out.println("====> Session rolled back <====");
				session.rollback();
			}
			
			SecurityService securityService = new SecurityService();
			securityService.issueIdCard(employee);
			securityService.issueLaptop(employee);
			securityService.issueAccessories(employee);
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			message.acknowledge();
			
			MessageProducer securityAckProducer = session.createProducer(message.getJMSReplyTo());
			
			String msg = "Security App completed all tasks";
			TextMessage textMessage = session.createTextMessage(msg);
			
			securityAckProducer.send(textMessage);
			
			exceptionFlag++;
			
			if(exceptionFlag > 1) {
				session.commit();
			}
			
		} catch(JMSException ex) {
			System.out.println("Exception occured in security app listener");
			ex.printStackTrace();
			try {
				if(session.getAcknowledgeMode() == 0) {
					// Rollback the transaction in case of an exception
					session.rollback();
				} else {
					ex.printStackTrace();
				}
            } catch (JMSException e) {
            	e.printStackTrace();
            }
		}
		
	}

}
