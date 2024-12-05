package personal.learning.jms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/listener")
public class JMSListenerController {
	
	@Autowired
	private DefaultMessageListenerContainer messageListenerContainer1;
	
	@Autowired
	private DefaultMessageListenerContainer messageListenerContainer2;
	
	@Autowired
	private DefaultMessageListenerContainer messageListenerContainer3;
	
	@GetMapping(value = "/start/subscriber1")
	public void startSubscriber1() {
		System.out.println("TopicSubscriber1 started...");
		messageListenerContainer1.start();
	}
	
	@GetMapping(value = "/start/subscriber2")
	public void startSubscriber2() {
		System.out.println("TopicSubscriber2 started...");
		messageListenerContainer2.start();
	}
	
	@GetMapping(value = "/start/subscriber3")
	public void startSubscriber3() {
		System.out.println("TopicSubscriber3 started...");
		messageListenerContainer3.start();
	}

	@GetMapping(value = "/stop/subscriber1")
	public void stopSubscriber1() {
		System.out.println("TopicSubscriber1 stopped...");
		messageListenerContainer1.stop();
	}
	
	@GetMapping(value = "/stop/subscriber2")
	public void stopSubscriber2() {
		System.out.println("TopicSubscriber2 stopped...");
		messageListenerContainer2.stop();
	}
	
	@GetMapping(value = "/stop/subscriber3")
	public void stopSubscriber3() {
		System.out.println("TopicSubscriber3 stopped...");
		messageListenerContainer3.stop();
	}
	
	@GetMapping(value = "/status/subscriber1")
	public void statusSubscriber1() {
		System.out.println("TopicSubscriber1 status : " + messageListenerContainer1.isRunning());
	}
	
	@GetMapping(value = "/status/subscriber2")
	public void statusSubscriber2() {
		System.out.println("TopicSubscriber2 status : " + messageListenerContainer2.isRunning());
	}
	
	@GetMapping(value = "/status/subscriber3")
	public void statusSubscriber3() {
		System.out.println("TopicSubscriber3 status : " + messageListenerContainer3.isRunning());
	}
}
