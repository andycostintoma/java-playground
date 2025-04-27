package com.andy.rxjava.p3_basic_operators.utility;

import io.reactivex.rxjava3.core.Observable;

/**
 * Demonstrates the use of the repeat() operator in RxJava.
 * - Repeats the emissions a specified number of times.
 */
public class RepeatExample {

    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma")
                .repeat(2) // Repeat the emissions twice
                .subscribe(s -> System.out.println("Received: " + s));
    }
}
