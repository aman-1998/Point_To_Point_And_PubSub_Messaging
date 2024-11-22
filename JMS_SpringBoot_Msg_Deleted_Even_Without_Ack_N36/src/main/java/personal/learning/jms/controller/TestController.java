package personal.learning.jms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.JmsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import personal.learning.jms.sender.DemoMessageSender;
import personal.learning.jms.sender.DemonstrateMessageSender;
import personal.learning.jms.sender.DummyMessageSender;
import personal.learning.jms.sender.TestMessageSender;

@RestController
public class TestController {
	
	@Autowired
	private TestMessageSender testMessageSender;
	
	@Autowired
	private DemoMessageSender demoMessageSender;
	
	@Autowired
	private DummyMessageSender dummyMessageSender;
	
	@Autowired
	private DemonstrateMessageSender demonstrateMessageSender;
	
	@GetMapping("/send1")
	public ResponseEntity<Object> sendMessage1() {
		
		try {
			System.out.println("Sending message...");
			testMessageSender.send("Testing message!!");
		} catch(JmsException ex) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok("Message sent successfully!!");
	}
	
	@GetMapping("/send2")
	public ResponseEntity<Object> sendMessage2() {
		
		try {
			System.out.println("Sending message...");
			demoMessageSender.send("Demo message!!");
		} catch(JmsException ex) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok("Message sent successfully!!");
	}
	
	@GetMapping("/send3")
	public ResponseEntity<Object> sendMessage3() {
		
		try {
			System.out.println("Sending message...");
			dummyMessageSender.send("Dummy message!!");
		} catch(JmsException ex) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok("Message sent successfully!!");
	}
	
	@GetMapping("/send4")
	public ResponseEntity<Object> sendMessage4() {
		
		try {
			System.out.println("Sending message...");
			demonstrateMessageSender.send("Demonstration message!!");
		} catch(JmsException ex) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok("Message sent successfully!!");
	}
}
