package org.formation;

import java.time.Duration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class ReactiveController {

	@GetMapping("/pause/{duration}")
	public Mono<String> pause(@PathVariable long duration) {
		return Mono.delay(Duration.ofMillis(duration)).then(Mono.just("PAUSED " + duration/1000 + "s"));
	}
}
