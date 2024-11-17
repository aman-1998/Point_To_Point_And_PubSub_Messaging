package personal.learning.jms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import personal.learning.jms.sender.TestMessageSender;

@SpringBootTest
class JmsWithSpringBootApplicationTests {
	
	@Autowired
	private TestMessageSender testMessageSender;

	@Test
	void sendMessageTest() {
		System.out.println("Sending message...");
		testMessageSender.send("Testing message!!");
	}

}
