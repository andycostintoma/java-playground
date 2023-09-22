package com.andy.rxjava.p2_observable_observer.p2_factories;

import io.reactivex.rxjava3.core.Observable;

/**
 * This class demonstrates the usage of Observable.range() to create an Observable that emits
 * a consecutive range of integers.
 */
public class RangeObservableExample {

    /**
     * The main entry point of the program.
     *
     * @param args The command-line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // Create an Observable that emits a range of integers from 1 to 3
        Observable.range(1, 3)
               .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
