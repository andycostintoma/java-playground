package com.andy.rxjava.p2_observable_observer.p2_factories;

import io.reactivex.rxjava3.core.Observable;

/**
 * This class demonstrates the usage of {@link Observable#error(Throwable)} to create an
 * observable that immediately generates an onError event with the specified exception.
 */
public class ErrorObservableExample {

    /**
     * The main method of the program.
     *
     * @param args The command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // Create an observable that immediately generates an onError event with the specified exception
        Observable.error(new Exception("Crash and burn!"))
                .subscribe(
                    // onNext: Handle emitted values (won't be executed)
                    i -> System.out.println("RECEIVED: " + i),
                    // onError: Handle errors
                    e -> System.out.println("Error captured: " + e),
                    // onComplete: Handle completion (won't be executed)
                    () -> System.out.println("Done!")
                );

        // You can also provide the exception using a lambda expression to create a separate exception instance for each Observer
        Observable.error(() -> new Exception("Crash and burn!"))
                .subscribe(
                    i -> System.out.println("RECEIVED: " + i),
                    e -> System.out.println("Error captured: " + e),
                    () -> System.out.println("Done!")
                );
    }
}
