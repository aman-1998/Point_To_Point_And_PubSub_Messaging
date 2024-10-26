package personal.learning.activemq.jms.service;

import personal.learning.activemq.jms.model.Employee;

public class WellnessService {

	public void setHealthInsurance(Employee employee) {
		System.out.println("Health Insurance covered for " + employee.getFirstName());
	}

	public void setHalfYearlyHealthCheckUp(Employee employee) {
		System.out.println("Half yearly Health-check up covered for " + employee.getFirstName());
	}

}
