package com.andy.rxjava.p2_observable_observer.p2_factories;

import io.reactivex.rxjava3.core.Observable;

/**
 * This class demonstrates the usage of {@link Observable#defer(io.reactivex.rxjava3.functions.Supplier)}
 * to create a separate state for each Observer by generating a fresh Observable for every subscription.
 */
public class DeferObservableExample {

    private static final int start = 1;
    private static int count = 3;

    /**
     * The main method of the program.
     *
     * @param args The command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // Create an Observable using Observable.defer() to reflect changes in state for each Observer
        Observable<Integer> source = Observable.defer(() ->
                Observable.range(start, count));

        // First subscription
        source.subscribe(i -> System.out.println("Observer 1: " + i));

        // Modify count
        count = 5;

        // Second subscription
        source.subscribe(i -> System.out.println("Observer 2: " + i));
    }
}
