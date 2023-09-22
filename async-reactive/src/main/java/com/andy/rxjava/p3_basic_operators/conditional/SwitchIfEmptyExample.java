package com.andy.rxjava.p3_basic_operators.conditional;

import io.reactivex.rxjava3.core.Observable;

/**
 * Demonstrates the use of switchIfEmpty() to specify a different Observable
 * when the source Observable is empty.
 */
public class SwitchIfEmptyExample {
    public static void main(String[] args) {
        // Create an Observable with some strings
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma")
                .filter(s -> s.startsWith("Z"));

        // Use switchIfEmpty to specify a different Observable when the source is empty
        source.switchIfEmpty(Observable.just("Zeta", "Eta", "Theta"))
                .subscribe(
                        item -> System.out.println("RECEIVED: " + item),
                        error -> System.err.println("RECEIVED ERROR: " + error),
                        () -> System.out.println("Completed")
                );
    }
}
