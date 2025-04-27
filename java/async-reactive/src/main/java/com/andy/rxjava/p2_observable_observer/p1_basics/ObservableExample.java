package com.andy.rxjava.p2_observable_observer.p1_basics;

import io.reactivex.rxjava3.core.Observable;

/**
 * This class demonstrates the workings of Observables in RxJava, including the creation of Observables using {@link Observable#create},
 * handling {@code onNext()}, {@code onComplete()}, and {@code onError()} events, and chaining operators.
 */
public class ObservableExample {

    /**
     * The main entry point of the program.
     *
     * @param args The command-line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // Create a source Observable using Observable.create()
        Observable<String> source = Observable.create(emitter -> {
            try {
                // Emit items one at a time
                emitter.onNext("Alpha");
                emitter.onNext("Beta");
                emitter.onNext("Gamma");
                // Signal the completion of emissions
                emitter.onComplete();
            } catch (Throwable e) {
                // Handle any errors and push them down the chain
                emitter.onError(e);
            }
        });

        // Subscribe to the Observable and define actions for onNext and onError events
        source.subscribe(
            item -> System.out.println("Received Item: " + item),
            error -> System.err.println("Error: " + error.getMessage()),
            () -> System.out.println("Observable Completed")
        );

        // Chaining operators map() and filter() to transform and filter emitted data
        // Each operator returns a new Observable that can be further processed.
        source.map(String::length)
              .filter(length -> length >= 5)
              .subscribe(
                  length -> System.out.println("Filtered Length: " + length),
                  error -> System.err.println("Error: " + error.getMessage()),
                  () -> System.out.println("Observable Completed")
              );
    }
}
