package com.andy.rxjava.p3_basic_operators.suppressing;

import io.reactivex.rxjava3.core.Observable;

/**
 * Demonstrates the use of the distinctUntilChanged() operator to ignore consecutive duplicate emissions.
 */
public class DistinctUntilChangedExample {
    public static void main(String[] args) {
        // Example 1: Simple distinctUntilChanged
        Observable.just(1, 1, 1, 2, 2, 3, 3, 2, 1, 1)
                .distinctUntilChanged()
                .subscribe(i -> System.out.println("RECEIVED: " + i));

        // Example 2: DistinctUntilChanged with key selector
        Observable.just("Alpha", "Beta", "Zeta", "Eta", "Gamma", "Delta")
                .distinctUntilChanged(String::length)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
