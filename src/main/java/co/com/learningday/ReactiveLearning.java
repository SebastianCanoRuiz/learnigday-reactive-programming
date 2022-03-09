package co.com.learningday;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ReactiveLearning {

    private static UtilsFuntions utilsFuntions;

    public static void main(String[] args) {
        utilsFuntions = new UtilsFuntions();
        //monoVsFlux();
        //map();
        //flatmap();
        //filter();
        //switchIfEmpty();
//        zip();
//        onErroResume();
    }
    public static void monoVsFlux(){
        Mono.just("Hola Mundo")
                .delayElement(Duration.ofSeconds(2))
                .map(string-> string.toUpperCase())
                .doOnNext(string-> System.out.println("ejemplo de Mono " + string)).log()
                .subscribe();

        List<String> nombres= new ArrayList<>();
        nombres.add("Juan");
        nombres.add("Angie");
        nombres.add("Jhon");
        nombres.add("Jesus");
        nombres.add("Omar");

        Flux<String> reactive= Flux.fromStream(nombres.stream());
        reactive.map(string-> string.toUpperCase())
                .doOnNext(string->System.out.println(Instant.now().toString()+ " + " + string))
                .log()
                .subscribe();
    }

    public static void map(){
        Mono<Integer> resactive=Mono.just(10);
        resactive.map(integer -> integer*100)
                .doOnNext(temp->System.out.println("map "+temp))
                .subscribe();
    }

    public static void flatmap(){
        Mono<Integer> resactive=Mono.just(100);
        resactive.flatMap(temp-> utilsFuntions.stringFaltMap(temp))
                .doOnNext(temp->System.out.println("flatmap "+temp))
                .subscribe();
    }

    public static void filter(){
        List<String> nombres= new ArrayList<>();
        nombres.add("Juan");
        nombres.add("Angie");
        nombres.add("Jhon");
        nombres.add("Jesus");
        nombres.add("Omar");

        Flux<String> reactive= Flux.fromStream(nombres.stream());
        reactive.filter(temp-> temp.startsWith("J"))
                .doOnNext(temp->System.out.println("filter "+temp))
                .subscribe();
    }

    public static void switchIfEmpty(){
        List<String> nombres= new ArrayList<>();
        nombres.add("Juan");
        nombres.add("Angie");
        nombres.add("Jhon");
        nombres.add("Jesus");
        nombres.add("Omar");

        Flux<String> reactive= Flux.fromStream(nombres.stream());
        reactive.filter(temp-> temp.startsWith("Z"))
                .doOnNext(temp->System.out.println("filter "+temp))
                .switchIfEmpty(temp->System.out.println("No se encontraron elementos"))
                .subscribe();
    }

    public static void zip() {
        Mono.zip(utilsFuntions.doSomething1(), utilsFuntions.doSomething2())
                .doOnNext(tuple->System.out.println("resultado 1:"+tuple.getT1()+" resultado 2 "+tuple.getT2()))
                .subscribe();
        try{
            Thread.sleep(10000);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void onErroResume(){
        Mono.just("prueba de error")
                .flatMap(temp-> Mono.error(new Exception("Upps hubo un fallo")))
                .onErrorResume(Exception.class, error-> {
                    System.out.println("Fallo: "+ error.getMessage());
                    return Mono.empty();
                }).subscribe();
    }
}