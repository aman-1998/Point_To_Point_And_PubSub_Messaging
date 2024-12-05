package personal.learning.jms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class JmsWithSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(JmsWithSpringBootApplication.class, args);
	}

}
