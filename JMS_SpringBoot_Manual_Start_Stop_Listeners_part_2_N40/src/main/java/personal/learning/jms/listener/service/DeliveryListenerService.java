package personal.learning.jms.listener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.jms.listener.MessageListenerContainer;

public class DeliveryListenerService {
	
	@Autowired
    private JmsListenerEndpointRegistry registry;
	
	public void stopListener(String listenerId) {
        MessageListenerContainer container = registry.getListenerContainer(listenerId);
        if (container != null && container.isRunning()) {
            container.stop();
            System.out.println("Listener with ID " + listenerId + " stopped.");
        }
    }

    public void startListener(String listenerId) {
        MessageListenerContainer container = registry.getListenerContainer(listenerId);
        if (container != null && !container.isRunning()) {
            container.start();
            System.out.println("Listener with ID " + listenerId + " started.");
        }
    }
    
    public void currentStatus(String listenerId) {
    	MessageListenerContainer container = registry.getListenerContainer(listenerId);
        System.out.println("Listener with ID " + listenerId + " status : " + container.isRunning());
    }
}
