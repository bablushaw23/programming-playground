package com.example;


import com.example.publisher.PublisherImpl;
import com.example.publisher.SubscriptionImpl;
import com.example.subscriber.SubscriberImpl;

import java.time.Duration;

/*
    1. Publisher does not produce data unless subscriber requests for it
    2. Publisher can only produce items <= number subscriber requested. Publisher can also produce 0 items
    3. Subscriber can cancel the subscription. publisher should stop at the moment as subscriber is no longer interested consuming the data
    4. Publisher can send error signal to indicate something is wrong
 */
public class Demo {
    public static void main(String[] args) throws InterruptedException {
        demo3();
    }

    public static void demo1() {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
    }

//    public static void demo2() {
//        var publisher = new PublisherImpl();
//        var subscriber = new SubscriberImpl();
//        publisher.subscribe(subscriber);
//        subscriber.getSubscription().request(3);
//    }

    public static void demo2() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));

        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));

        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));

        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        // completed is called here as 10 items over
        System.out.println("No output supposed below");
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
    }

    public static void demo3() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        // asked for 3 and the cancelled
        subscriber.getSubscription().cancel();
        Thread.sleep(Duration.ofSeconds(2));
        // re-request for more but already cancelled so no data
        subscriber.getSubscription().request(3);
    }
}
