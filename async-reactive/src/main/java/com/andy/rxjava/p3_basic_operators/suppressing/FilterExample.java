package com.andy.rxjava.p3_basic_operators.suppressing;

import io.reactivex.rxjava3.core.Observable;

/**
 * Demonstrates the use of the filter() operator to filter emissions based on a condition.
 */
public class FilterExample {
    public static void main(String[] args) {
        // Create an Observable with some strings
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma")
                .filter(s -> s.length() != 5);

        // Use the filter() operator to filter out strings with a length of 5
        source.subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
