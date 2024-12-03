package personal.learning.jms.subscriber;

import org.springframework.jms.annotation.JmsListener;

public class TopicSubscriber1 {
	
	@JmsListener(destination = "${springjms.myTopic.sampleTopic}", id = "topicListener1", containerFactory = "jmsListenerContainerFactoryForPubsub")
	public void receiveMessage(String message) {
        System.out.println("Subscriber 1 received: " + message);
    }
}
