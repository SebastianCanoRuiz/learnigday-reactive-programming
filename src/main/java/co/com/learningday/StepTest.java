package co.com.learningday;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class StepTest {
    public static Mono<String> findOne() {
        return Mono.just("hola");
    }

    public static Flux<String> findAll() {
        return Flux.just("hola","que","tal","estas");
    }

    public static Flux<String> findWithDelay() {
        return Flux.just("hola","que","tal","estas").delaySequence(Duration.ofSeconds(7));
    }

}
