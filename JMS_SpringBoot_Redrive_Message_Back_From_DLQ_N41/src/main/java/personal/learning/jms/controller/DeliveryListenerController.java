package personal.learning.jms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import personal.learning.jms.listener.service.SampleListenerService;

@RestController
@RequestMapping("/samplelistener")
public class DeliveryListenerController {
	
	@Autowired
	private SampleListenerService sampleListenerService;
	
	@GetMapping(value = "/start")
	public void startListener(@RequestParam("listenerId") String listenerId) {
		sampleListenerService.startListener(listenerId);
	}

	@GetMapping(value = "/stop")
	public void stopListener(@RequestParam("listenerId") String listenerId) {
		sampleListenerService.stopListener(listenerId);
	}
	
	@GetMapping(value = "/status")
	public void currentStatus(@RequestParam("listenerId") String listenerId) {
		sampleListenerService.currentStatus(listenerId);
	}
}
