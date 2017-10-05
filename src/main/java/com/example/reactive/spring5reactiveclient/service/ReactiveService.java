package com.example.reactive.spring5reactiveclient.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.stream.Stream;


@SpringBootApplication
@RestController
public class ReactiveService {

    @GetMapping("/events/{id}")
    public Mono<Event> eventById(@PathVariable Long id){
        return Mono.just(new Event(id, new Date()));
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Event> events() {
        Flux<Event> eventFlux = Flux.fromStream(Stream.generate(() -> new Event(System.currentTimeMillis(), new Date())));
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));

        return Flux.zip(eventFlux, interval).map(Tuple2::getT1);
    }

    public static void main(String[] args) {
        SpringApplication.run(ReactiveService.class, args);
    }


}
