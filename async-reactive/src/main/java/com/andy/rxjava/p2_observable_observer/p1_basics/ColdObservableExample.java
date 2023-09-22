package com.andy.rxjava.p2_observable_observer.p1_basics;

import io.reactivex.rxjava3.core.Observable;

/**
 * This class demonstrates a cold Observable and how it replays emissions to multiple Observers.
 * Each Observer receives its own set of emissions.
 */
public class ColdObservableExample {

    /**
     * The main entry point of the program.
     *
     * @param args The command-line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // Create a cold Observable that emits integers from 1 to 3
        Observable<Integer> source = Observable.range(1, 3);

        // Observer 1 subscribes to the cold Observable
        source.subscribe(value -> System.out.println("Observer 1: " + value));

        // Observer 2 subscribes to the same cold Observable
        source.subscribe(value -> System.out.println("Observer 2: " + value));
    }
}
