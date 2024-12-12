package personal.learning.jms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import personal.learning.jms.listener.service.JMSListenerService;

@RestController
@RequestMapping("/listener")
public class JMSListenerController {
	
	@Autowired
	private JMSListenerService jmsListenerService;
	
	@GetMapping(value = "/start")
	public void startListener(@RequestParam("listenerId") String listenerId) {
		jmsListenerService.startListener(listenerId);
	}

	@GetMapping(value = "/stop")
	public void stopListener(@RequestParam("listenerId") String listenerId) {
		jmsListenerService.stopListener(listenerId);
	}
	
	@GetMapping(value = "/status")
	public void currentStatus(@RequestParam("listenerId") String listenerId) {
		jmsListenerService.currentStatus(listenerId);
	}
}
