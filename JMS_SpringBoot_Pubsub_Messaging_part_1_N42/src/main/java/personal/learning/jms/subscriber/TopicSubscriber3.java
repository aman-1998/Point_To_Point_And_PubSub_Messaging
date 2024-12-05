package personal.learning.jms.subscriber;

import org.springframework.jms.annotation.JmsListener;

public class TopicSubscriber3 {
	
	@JmsListener(destination = "${springjms.myTopic.sampleTopic}", id = "topicListener3", containerFactory = "jmsListenerContainerFactoryForPubsub")
	public void receiveMessage(String message) {
        System.out.println("Subscriber 3 received: " + message);
    }
}
