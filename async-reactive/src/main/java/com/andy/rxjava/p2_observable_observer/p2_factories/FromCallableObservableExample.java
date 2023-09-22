package com.andy.rxjava.p2_observable_observer.p2_factories;

import io.reactivex.rxjava3.core.Observable;

/**
 * This class demonstrates the usage of {@link Observable#fromCallable(java.util.concurrent.Callable)}
 * to perform a calculation or action lazily and emit the result to the Observer.
 */
public class FromCallableObservableExample {

    /**
     * The main method of the program.
     *
     * @param args The command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // Using Observable.fromCallable() to perform a calculation and emit the result
        Observable.fromCallable(() -> 1 / 0)
                .subscribe(
                        i -> System.out.println("Received: " + i),
                        e -> System.out.println("Error captured: " + e)
                );
    }
}
