package com.andy.rxjava.p2_observable_observer.p4_disposing;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.concurrent.TimeUnit;

/**
 * This class demonstrates the usage of Disposable to stop emissions and dispose of resources in RxJava.
 */
public class DisposableExample {
    public static void main(String[] args) {
        // Create an Observable that emits a value every second
        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);

        // Subscribe to the Observable and get a Disposable
        Disposable disposable = seconds.subscribe(
                l -> System.out.println("Received: " + l) // onNext
        );

        // Sleep for 5 seconds to allow emissions
        sleep(5000);

        // Dispose of the subscription, stopping emissions
        disposable.dispose();

        // Sleep for another 5 seconds to demonstrate no more emissions
        sleep(5000);
    }

    /**
     * Sleeps for the specified number of milliseconds.
     *
     * @param millis The duration to sleep in milliseconds.
     */
    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
