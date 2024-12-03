package personal.learning.jms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.JmsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import personal.learning.jms.sender.SampleMessageSender;

@RestController
public class TestController {
	
	@Autowired
	private SampleMessageSender sampleMessageSender;
	
	/*
	 * If the max-delivery-attempts is reached before 
	 * the TTL expires, the message is moved to the DLQ.
	 * 
	 * The message is rolled back, causing redelivery. The broker considers 
	 * it undeliverable and moves it to the DLQ (or a global DLQ if no 
	 * specific dead-letter-address is defined).
	 */
	@GetMapping("/send")
	public ResponseEntity<Object> sendMessage3() {
		
		try {
			System.out.println("Sending message...");
			sampleMessageSender.send("Sample message!!", 15000);
		} catch(JmsException ex) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok("Message sent successfully!!");
	}
	
}
