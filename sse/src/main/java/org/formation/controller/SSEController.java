package org.formation.controller;

import org.formation.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;

@RestController
public class SSEController {

    @Autowired
    private KafkaService kafkaService;

    @GetMapping(value = "/stream-kafka", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> stream() {
        return kafkaService.getKafkaFlux();
    }

    @GetMapping(path = "/stream-flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> streamFlux() {
	    return Flux.interval(Duration.ofSeconds(1))
	      .map(sequence -> "Flux - " + LocalTime.now().toString());
	}
}
