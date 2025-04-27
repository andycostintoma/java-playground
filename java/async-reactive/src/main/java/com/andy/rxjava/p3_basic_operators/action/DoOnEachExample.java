package com.andy.rxjava.p3_basic_operators.action;

import io.reactivex.rxjava3.core.Observable;

/**
 * Demonstrates the use of the doOnEach() operator in RxJava.
 * - Allows performing actions on onNext, onComplete, and onError events.
 */
public class DoOnEachExample {

    public static void main(String[] args) {
        Observable.just("One", "Two", "Three")
                .doOnEach(notification -> {
                    if (notification.isOnNext()) {
                        System.out.println("doOnEach() - onNext: " + notification.getValue());
                    } else if (notification.isOnError()) {
                        System.out.println("doOnEach() - onError: " + notification.getError());
                    } else if (notification.isOnComplete()) {
                        System.out.println("doOnEach() - onComplete");
                    }
                })
                .subscribe(i -> System.out.println("Received: " + i));
    }
}
