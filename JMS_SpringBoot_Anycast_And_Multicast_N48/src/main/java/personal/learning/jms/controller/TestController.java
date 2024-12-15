package personal.learning.jms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.JmsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import personal.learning.jms.publisher.HRMSTopicPublisher;

@RestController
public class TestController {
	
	@Autowired
	private HRMSTopicPublisher hrmsTopicPublisher;
	
	@GetMapping("/send1")
	public ResponseEntity<Object> sendMessage1() {
		
		try {
			System.out.println("Sending message to topic...");
			hrmsTopicPublisher.publish1("Sample topic-message!!");
		} catch(JmsException ex) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok("Message sent successfully to topic!!");
	}
	
	@GetMapping("/send2")
	public ResponseEntity<Object> sendMessage2() {
		
		try {
			System.out.println("Sending message to topic...");
			hrmsTopicPublisher.publish2("Sample topic-message!!");
		} catch(JmsException ex) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok("Message sent successfully to topic!!");
	}
	
	@GetMapping("/send3")
	public ResponseEntity<Object> sendMessage3() {
		
		try {
			System.out.println("Sending message to topic...");
			hrmsTopicPublisher.publish3("Sample topic-message!!");
		} catch(JmsException ex) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok("Message sent successfully to topic!!");
	}
	
}
