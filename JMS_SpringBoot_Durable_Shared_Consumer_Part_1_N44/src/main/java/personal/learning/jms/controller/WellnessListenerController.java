package personal.learning.jms.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/listener/wellness")
public class WellnessListenerController {
	
	@Autowired
	private DefaultMessageListenerContainer messageListenerContainerWell1;
	
	@Autowired
	private DefaultMessageListenerContainer messageListenerContainerWell2;
	
	@GetMapping(value = "/start/{id}")
	public void startWellnessSubscriber1(@PathVariable("id") String id) {
		if(StringUtils.equals(id, "1")) {
			System.out.println("WellnessConsumer1 started...");
			messageListenerContainerWell1.start();
		} else if(StringUtils.equals(id, "2")) {
			System.out.println("WellnessConsumer2 started...");
			messageListenerContainerWell2.start();
		} 
	}

	@GetMapping(value = "/stop/{id}")
	public void stopWellnessSubscriber1(@PathVariable("id") String id) {
		if(StringUtils.equals(id, "1")) {
			System.out.println("WellnessConsumer1 stopped...");
			messageListenerContainerWell1.stop();
		} else if(StringUtils.equals(id, "2")) {
			System.out.println("WellnessConsumer2 stopped...");
			messageListenerContainerWell2.stop();
		}
	}
	
	@GetMapping(value = "/status/{id}")
	public void statusWellnessSubscriber1(@PathVariable("id") String id) {
		if(StringUtils.equals(id, "1")) {
			System.out.println("WellnessConsumer1 status : " + messageListenerContainerWell1.isRunning());
		} else if(StringUtils.equals(id, "2")) {
			System.out.println("WellnessConsumer2 status : " + messageListenerContainerWell2.isRunning());
		}
	}
	
}
