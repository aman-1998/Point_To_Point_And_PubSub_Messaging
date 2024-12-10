package personal.learning.jms.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/listener/payroll")
public class PayrollListenerController {
	
	@Autowired
	private DefaultMessageListenerContainer messageListenerContainerPay1;
	
	@Autowired
	private DefaultMessageListenerContainer messageListenerContainerPay2;
	
	@GetMapping(value = "/start/{id}")
	public void startPayrollSubscriber1(@PathVariable("id") String id) {
		if(StringUtils.equals(id, "1")) {
			System.out.println("PayrollConsumer1 started...");
			messageListenerContainerPay1.start();
		} else if(StringUtils.equals(id, "2")) {
			System.out.println("PayrollConsumer2 started...");
			messageListenerContainerPay2.start();
		}
	}
	
	@GetMapping(value = "/stop/{id}")
	public void stopPayrollSubscriber1(@PathVariable("id") String id) {
		if(StringUtils.equals(id, "1")) {
			System.out.println("PayrollConsumer1 stopped...");
			messageListenerContainerPay1.stop();
		} else if(StringUtils.equals(id, "2")) {
			System.out.println("PayrollConsumer2 stopped...");
			messageListenerContainerPay2.stop();
		}
	}
	
	@GetMapping(value = "/status/{id}")
	public void statusPayrollSubscriber1(@PathVariable("id") String id) {
		if(StringUtils.equals(id, "1")) {
			System.out.println("PayrollConsumer1 status : " + messageListenerContainerPay1.isRunning());
		} else if(StringUtils.equals(id, "2")) {
			System.out.println("PayrollConsumer2 status : " + messageListenerContainerPay2.isRunning());
		}
		
	}
	
}
