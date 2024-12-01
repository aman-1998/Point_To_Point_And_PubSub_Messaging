package personal.learning.jms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListenerController {
	
	@Autowired
	@Qualifier("messageListenerContainer3")
	private DefaultMessageListenerContainer messageListenerContainer;
	
	@GetMapping(value = "/startPaymentListener")
	public void startListener() {
	    messageListenerContainer.start();
	    System.out.println("Listener for payment queue started.");
	}

	@GetMapping(value = "/stopPaymentListener")
	public void stopListener() {
	    messageListenerContainer.stop();
	    System.out.println("Listener for payement queue stopped.");
	}
}
