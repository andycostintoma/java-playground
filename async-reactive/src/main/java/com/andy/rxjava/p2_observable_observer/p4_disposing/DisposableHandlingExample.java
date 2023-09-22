package com.andy.rxjava.p2_observable_observer.p4_disposing;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.ResourceObserver;
import java.util.concurrent.TimeUnit;

/**
 * This class demonstrates the usage of a custom Observer and ResourceObserver to handle disposables in RxJava.
 */
public class DisposableHandlingExample {
    public static void main(String[] args) {
        // Create an Observable that emits a value every second
        Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS);

        // Create a custom Observer that handles disposables
        ResourceObserver<Long> myObserver = new ResourceObserver<>() {
            @Override
            public void onNext(Long value) {
                System.out.println(value);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Done!");
            }
        };

        // Subscribe to the Observable with the custom Observer and capture the Disposable
        Disposable disposable = source.subscribeWith(myObserver);

        // Sleep for a while to allow emissions
        sleep(5000);

        // Dispose of the subscription manually
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
