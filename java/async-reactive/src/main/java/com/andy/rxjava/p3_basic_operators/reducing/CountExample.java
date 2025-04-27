package com.andy.rxjava.p3_basic_operators.reducing;

import io.reactivex.rxjava3.core.Observable;

/**
 * Demonstrates the use of the count() operator to count the number of emitted items.
 */
public class CountExample {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma")
                  .count()
                  .subscribe(count -> System.out.println("Received: " + count));
    }
}
