package com.andy.rxjava.p3_basic_operators.suppressing;

import io.reactivex.rxjava3.core.Observable;

/**
 * Demonstrates the use of the distinct() operator to emit unique values.
 */
public class DistinctExample {
    public static void main(String[] args) {
        // Example 1: Simple distinct
        Observable.just("Alpha", "Beta", "Gamma")
                .map(String::length)
                .distinct()
                .subscribe(i -> System.out.println("RECEIVED: " + i));

        // Example 2: Distinct with key selector
        Observable.just("Alpha", "Beta", "Gamma")
                .distinct(String::length)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
