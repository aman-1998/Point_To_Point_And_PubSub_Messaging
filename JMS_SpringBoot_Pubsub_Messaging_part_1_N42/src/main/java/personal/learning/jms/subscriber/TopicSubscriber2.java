package personal.learning.jms.subscriber;

import org.springframework.jms.annotation.JmsListener;

public class TopicSubscriber2 {
	
	@JmsListener(destination = "${springjms.myTopic.sampleTopic}", id = "topicListener2", containerFactory = "jmsListenerContainerFactoryForPubsub")
	public void receiveMessage(String message) {
        System.out.println("Subscriber 2 received: " + message);
    }
}
