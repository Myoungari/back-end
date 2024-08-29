package myongari.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MyongariBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyongariBackendApplication.class, args);
	}

}
