package com.andy.rxjava.p3_basic_operators.conditional;

import io.reactivex.rxjava3.core.Observable;

public class DefaultIfEmptyExample {
    public static void main(String[] args) {
        // Simulated list of items
        Observable<String> items = Observable.just("Alpha", "Beta");

        // Using defaultIfEmpty() to emit "None" if no items start with "Z"
        System.out.println("=== Items Starting with 'Z' ===");
        items
            .filter(s -> s.startsWith("Z")) // Filter items that start with 'Z'
            .defaultIfEmpty("None") // Emit "None" if the source is empty
            .subscribe(
                result -> System.out.println("Result: " + result),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Observation completed.")
            );
    }
}
