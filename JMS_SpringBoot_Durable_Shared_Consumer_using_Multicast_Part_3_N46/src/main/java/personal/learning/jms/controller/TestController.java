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
	
	@GetMapping("/send")
	public ResponseEntity<Object> sendMessage() {
		
		try {
			System.out.println("Sending message to topic...");
			hrmsTopicPublisher.publish("Sample topic-message!!");
		} catch(JmsException ex) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok("Message sent successfully to topic!!");
	}
	
}
