package personal.learning.jms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import personal.learning.jms.listener.service.DeliveryListenerService;
import personal.learning.jms.receiver.DeliveryMessageReceiver;
import personal.learning.jms.receiver.OrderMessageReceiver;
import personal.learning.jms.receiver.PaymentMessageReceiver;
import personal.learning.jms.sender.DeliveryMessageSender;
import personal.learning.jms.sender.OrderMessageSender;
import personal.learning.jms.sender.PaymentMessageSender;

@Configuration
@ComponentScan(basePackages = {"personal.learning.jms"})
public class AppConfig {
	
	@Bean
	public OrderMessageSender orderMessageSender() {
		return new OrderMessageSender();
	}
	
	@Bean
	public OrderMessageReceiver orderMessageReceiver() {
		return new OrderMessageReceiver();
	}
	
	@Bean
	public PaymentMessageSender paymentMessageSender() {
		return new PaymentMessageSender();
	}
	
	@Bean
	public PaymentMessageReceiver paymentMessageReceiver() {
		return new PaymentMessageReceiver();
	}
	
	@Bean
	public DeliveryListenerService deliveryListenerService() {
		return new DeliveryListenerService();
	}
	
	@Bean
	public DeliveryMessageSender deliveryMessageSender() {
		return new DeliveryMessageSender();
	}
	
	@Bean
	public DeliveryMessageReceiver deliveryMessageReceiver() {
		return new DeliveryMessageReceiver();
	}
	
}
