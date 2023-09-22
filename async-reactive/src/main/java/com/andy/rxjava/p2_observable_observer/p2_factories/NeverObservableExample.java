package com.andy.rxjava.p2_observable_observer.p2_factories;

import io.reactivex.rxjava3.core.Observable;

/**
 * This class demonstrates the usage of {@link Observable#never()} to create an observable
 * that never emits any values and never completes.
 */
public class NeverObservableExample {

    /**
     * The main method of the program.
     *
     * @param args The command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // Create an observable that never emits and never completes
        Observable<String> neverObservable = Observable.never();

        // Subscribe to the neverObservable
        neverObservable.subscribe(
            // onNext: Print emitted values (won't be executed)
            System.out::println,
            // onError: Handle errors (won't be executed)
            Throwable::printStackTrace,
            // onComplete: Handle completion (won't be executed)
            () -> System.out.println("Done!")
        );

        // Sleep for 3 seconds to keep the application alive
        sleep(3000);
    }

    /**
     * A utility method to pause the current thread for a specified number of milliseconds.
     *
     * @param millis The number of milliseconds to sleep.
     */
    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
