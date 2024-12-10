package personal.learning.jms.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/listener/security")
public class SecurityListenerController {
	
	@Autowired
	private DefaultMessageListenerContainer messageListenerContainerSec1;
	
	@Autowired
	private DefaultMessageListenerContainer messageListenerContainerSec2;
	
	@GetMapping(value = "/start/{id}")
	public void startSecuritySubscriber1(@PathVariable("id") String id) {
		if(StringUtils.equals(id, "1")) {
			System.out.println("SecurityConsumer1 started...");
			messageListenerContainerSec1.start();
		} else if(StringUtils.equals(id, "2")) {
			System.out.println("SecurityConsumer2 started...");
			messageListenerContainerSec2.start();
		}
		
	}
	
	@GetMapping(value = "/stop/{id}")
	public void stopSecuritySubscriber1(@PathVariable("id") String id) {
		if(StringUtils.equals(id, "1")) {
			System.out.println("SecurityConsumer1 stopped...");
			messageListenerContainerSec1.stop();
		} else if(StringUtils.equals(id, "2")) {
			System.out.println("SecurityConsumer2 stopped...");
			messageListenerContainerSec2.stop();
		}
	}
	
	@GetMapping(value = "/status/{id}")
	public void statusSecurityubscriber1(@PathVariable("id") String id) {
		if(StringUtils.equals(id, "1")) {
			System.out.println("SecurityConsumer1 status : " + messageListenerContainerSec1.isRunning());
		} else if(StringUtils.equals(id, "2")) {
			System.out.println("SecurityConsumer2 status : " + messageListenerContainerSec2.isRunning());
		}
	}
	
}
