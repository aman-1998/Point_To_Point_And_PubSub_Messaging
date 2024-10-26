package personal.learning.activemq.jms.service;

import personal.learning.activemq.jms.model.Employee;

public class SecurityService {

	public void issueIdCard(Employee employee) {
		System.out.println("Id card issued to " + employee.getFirstName());
	}

	public void issueLaptop(Employee employee) {
		System.out.println("Laptop delivered to " + employee.getFirstName());
	}

	public void issueAccessories(Employee employee) {
		System.out.println("Accessories delivered to " + employee.getFirstName());
	}

}
