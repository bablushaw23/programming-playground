package com.example.lec2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class MonoSubscribe {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger(MonoSubscribe.class);

        // Here we will use Subscriber function offered by Reactor itself
        var mono = Mono.just(1);

//        var mono=Mono.just(1)
//                .map(e->e/0);    // triggering onError(), have to do by map as direct 1/0 results compile error

        // while implementing our own Subscriber interface, we had to define onNext(), onError(), onComplete(), onSubscribe()
        mono.subscribe(
                i -> log.info("Received:{}", i),       // onNext: define consumer behavior to do action with stream output
                err -> log.error("error", err),        // (Optional) defining onError()
                () -> log.info("Completed"),          // (Optional) defining onComplete()
                subscription -> subscription.request(1)     // (Optional) defining onSubscribe()
        );
    }
}
