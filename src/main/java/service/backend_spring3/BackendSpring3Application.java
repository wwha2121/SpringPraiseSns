package service.backend_spring3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class BackendSpring3Application {


	public static void main(String[] args) {

		SpringApplication.run(BackendSpring3Application.class, args);
	}

}
