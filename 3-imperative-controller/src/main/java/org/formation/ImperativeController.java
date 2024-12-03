package org.formation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ImperativeController {

	@GetMapping("/pause/{duration}")
	public String pause(@PathVariable long duration) throws InterruptedException {
		Thread.sleep(duration);
		return "paused " + duration/1000 + "s";
	}
}
