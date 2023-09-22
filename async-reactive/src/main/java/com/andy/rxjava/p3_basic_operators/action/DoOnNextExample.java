package com.andy.rxjava.p3_basic_operators.action;

import io.reactivex.rxjava3.core.Observable;

/**
 * Demonstrates the use of the doOnNext() and doAfterNext() operators in RxJava.
 * - Allows peeking at values before and after they flow downstream.
 */
public class DoOnNextExample {

    public static void main(String[] args) {
        // Example using doOnNext() to peek at values before they flow downstream
        Observable.just("Alpha", "Beta", "Gamma")
                .doOnNext(s -> System.out.println("doOnNext() - Processing: " + s))
                .map(String::length)
                .subscribe(i -> System.out.println("doOnNext() - Received: " + i));

        System.out.println();

        // Example using doAfterNext() to peek at values after they flow downstream
        Observable.just("Alpha", "Beta", "Gamma")
                .doAfterNext(s -> System.out.println("doAfterNext() - After: " + s))
                .map(String::length)
                .subscribe(i -> System.out.println("doAfterNext() - Received: " + i));
    }
}
