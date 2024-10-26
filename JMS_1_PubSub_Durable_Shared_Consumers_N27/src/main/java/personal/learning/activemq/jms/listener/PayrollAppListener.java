package personal.learning.activemq.jms.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import personal.learning.activemq.jms.model.Employee;
import personal.learning.activemq.jms.service.PayrollService;

public class PayrollAppListener implements MessageListener {
	
	private Session session;
	
	private String consumerName;
	
	public PayrollAppListener(Session session, String consumerName) {
		this.session = session;
		this.consumerName = consumerName;
	}

	public void onMessage(Message message) {

		try {
			Employee employee = message.getBody(Employee.class);
			
			System.out.println("Message received by " + consumerName + ": " + employee);
			
			PayrollService payrollService = new PayrollService();
			payrollService.setSalary(employee);
			payrollService.setBankName(employee);
			payrollService.setIfscCode(employee);
			payrollService.setAccountNo(employee);
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			message.acknowledge();
			
			MessageProducer payrollAckProducer = session.createProducer(message.getJMSReplyTo());
			
			String msg = "Payroll App completed all tasks";
			TextMessage textMessage = session.createTextMessage(msg);
			
			payrollAckProducer.send(textMessage);
			
		} catch(JMSException ex) {
			System.out.println("Exception occured in payroll app listener");
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
