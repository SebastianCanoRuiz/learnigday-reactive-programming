package co.com.learningday;

import reactor.core.publisher.Mono;

import java.time.Duration;

public class UtilsFuntions {

    public Mono<String> stringFaltMap(Integer integer){
        return Mono.just("Hola el valor es: "+ integer);
    }

    public Mono<String> doSomething1(){
        return Mono.just("hacer algo1")
                .delayElement(Duration.ofSeconds(5))
                .map(temp-> temp.toUpperCase())
                .doOnNext(temp->System.out.println(temp));
    }
    public Mono<String> doSomething2(){
        return Mono.just("hacer algo2")
                .delayElement(Duration.ofSeconds(2))
                .map(temp-> temp.toUpperCase())
                .doOnNext(temp->System.out.println(temp));
    }
}
