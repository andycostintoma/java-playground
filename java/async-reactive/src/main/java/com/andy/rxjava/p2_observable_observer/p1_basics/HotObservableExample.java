package com.andy.rxjava.p2_observable_observer.p1_basics;

import io.reactivex.rxjava3.subjects.PublishSubject;

/**
 * This class demonstrates a hot observable using a custom event simulation.
 */
public class HotObservableExample {

    public static void main(String[] args) {
        // Create a PublishSubject to represent a hot observable
        PublishSubject<String> hotObservable = PublishSubject.create();

        // First observer
        hotObservable.subscribe(s -> System.out.println("Observer 1: " + s));

        // Simulate emitting events
        hotObservable.onNext("Event 1");
        hotObservable.onNext("Event 2");

        // Second observer
        hotObservable.subscribe(s -> System.out.println("Observer 2: " + s));

        // More simulated events
        hotObservable.onNext("Event 3");
        hotObservable.onNext("Event 4");
    }
}
