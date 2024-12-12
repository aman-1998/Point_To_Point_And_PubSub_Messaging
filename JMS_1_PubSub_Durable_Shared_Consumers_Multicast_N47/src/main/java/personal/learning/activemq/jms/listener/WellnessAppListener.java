package personal.learning.activemq.jms.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import personal.learning.activemq.jms.model.Employee;
import personal.learning.activemq.jms.service.WellnessService;

public class WellnessAppListener implements MessageListener {
	
	private Session session;
	
	private String consumerName;
	
	public WellnessAppListener(Session session, String consumerName) {
		this.session = session;
		this.consumerName = consumerName;
	}

	public void onMessage(Message message) {
		
		try {
			Employee employee = message.getBody(Employee.class);
			
			System.out.println("Message received by " + consumerName + ": " + employee);
			
			WellnessService wellnessService = new WellnessService();
			wellnessService.setHealthInsurance(employee);
			wellnessService.setHalfYearlyHealthCheckUp(employee);
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			message.acknowledge();
			
			MessageProducer wellnessAckProducer = session.createProducer(message.getJMSReplyTo());
			
			String msg = "Wellness App completed all tasks";
			TextMessage textMessage = session.createTextMessage(msg);
			
			wellnessAckProducer.send(textMessage);
			
		} catch(JMSException ex) {
			System.out.println("Exception occured in wellness app listener");
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
