package org.formation.restclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestclientApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(RestclientApplication.class);
// prevent SpringBoot from starting a web server
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);

	}

}
