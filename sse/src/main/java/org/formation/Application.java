package org.formation;

import java.time.Duration;
import java.time.LocalTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@SpringBootApplication
@RestController
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	
}
