package personal.learning.activemq.jms.service;

import personal.learning.activemq.jms.model.Employee;

public class PayrollService {

	public void setSalary(Employee employee) {
		System.out.println("Salary set for " + employee.getFirstName());
	}

	public void setBankName(Employee employee) {
		System.out.println("Bank Name : AXIS");
	}

	public void setIfscCode(Employee employee) {
		System.out.println("IFSC Code : 00337");
	}

	public void setAccountNo(Employee employee) {
		System.out.println("Account no. of " + employee.getFirstName() + " : 992838888222");
	}

}
