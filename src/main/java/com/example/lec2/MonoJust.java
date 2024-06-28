package com.example.lec2;

import com.example.pubsub.subscriber.SubscriberImpl;
import reactor.core.publisher.Mono;

public class MonoJust {
    public static void main(String[] args) {
        var mono= Mono.just("Sample");  // publisher
        var subscriber=new SubscriberImpl();    // init subscriber
        mono.subscribe(subscriber);     // assigning subscriber to publisher

        subscriber.getSubscription().request(10);       // we have to request, or publisher wont publish
        // It will print Received: Sample -> completed!
    }
}
