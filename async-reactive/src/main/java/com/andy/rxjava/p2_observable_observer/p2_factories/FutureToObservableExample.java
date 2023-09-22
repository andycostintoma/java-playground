package com.andy.rxjava.p2_observable_observer.p2_factories;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.Future;
import java.util.concurrent.Executors;

/**
 * This class demonstrates how to convert a java.util.concurrent.Future into an RxJava Observable
 * and perform operations on the result.
 */
public class FutureToObservableExample {

    /**
     * The main entry point of the program.
     *
     * @param args The command-line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // Create a Future representing an asynchronous computation (e.g., from an external library)
        Future<String> future = createFuture();

        // Convert the Future into an Observable
        Observable<String> observable = Observable.fromFuture(future);

        // Perform operations on the result asynchronously
        observable.observeOn(Schedulers.io()) // Specify the scheduler for downstream operations
                  .map(String::length) // Map the result to its length
                  .subscribe(length -> System.out.println("Length: " + length));
    }

    /**
     * Simulates the creation of a Future representing an asynchronous computation.
     *
     * @return A Future with a result.
     */
    private static Future<String> createFuture() {
        // Use an executor service to submit a task that returns a result after a delay
        return Executors.newSingleThreadExecutor().submit(() -> {
            Thread.sleep(2000); // Simulate some asynchronous work
            return "RxJava is awesome!";
        });
    }
}
