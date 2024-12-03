package personal.learning.jms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paylistener")
public class PayListenerController {
	
	@Autowired
	@Qualifier("messageListenerContainer3")
	private DefaultMessageListenerContainer messageListenerContainer;
	
	@GetMapping(value = "/start")
	public void startListener() {
		System.out.println(messageListenerContainer.isRunning());
	    messageListenerContainer.start();
	    System.out.println("Listener for payment queue started.");
	}

	@GetMapping(value = "/stop")
	public void stopListener() {
		System.out.println(messageListenerContainer.isRunning());
	    messageListenerContainer.stop();
	    System.out.println("Listener for payement queue stopped.");
	}
	
	@GetMapping(value = "/status")
	public void currentStatus() {
		System.out.println("Current status of Payment Listener : " + messageListenerContainer.isRunning());
	}
}
