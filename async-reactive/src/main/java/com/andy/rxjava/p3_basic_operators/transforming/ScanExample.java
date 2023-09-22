package com.andy.rxjava.p3_basic_operators.transforming;

import io.reactivex.rxjava3.core.Observable;

/**
 * Demonstrates the use of the scan() operator to calculate rolling sums and counts of emitted values.
 */
public class ScanExample {
    public static void main(String[] args) {
        // Example 1: Rolling sum of emitted integers
        Observable.just(5, 3, 7)
                  .scan(Integer::sum)
                  .subscribe(s -> System.out.println("Received: " + s));

        // Example 2: Rolling count of emitted strings
        Observable.just("Alpha", "Beta", "Gamma")
                  .scan(0, (total, next) -> total + 1)
                  .skip(1) // Skip the initial value emitted by scan()
                  .subscribe(s -> System.out.println("Received: " + s));
    }
}
