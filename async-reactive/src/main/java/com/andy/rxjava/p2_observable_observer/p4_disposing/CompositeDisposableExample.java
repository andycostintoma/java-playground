package com.andy.rxjava.p2_observable_observer.p4_disposing;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.concurrent.TimeUnit;

public class CompositeDisposableExample {
    private static final CompositeDisposable disposables = new CompositeDisposable();

    public static void main(String[] args) {
        // Create an Observable that emits a value every second
        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);

        // Subscribe and capture disposables for two Observers
        Disposable disposable1 = seconds
            .subscribe(l -> System.out.println("Observer 1: " + l));
        Disposable disposable2 = seconds
            .subscribe(l -> System.out.println("Observer 2: " + l));

        // Put both disposables into the CompositeDisposable
        disposables.addAll(disposable1, disposable2);

        // Sleep for 5 seconds to allow emissions
        sleep(5000);

        // Dispose of all disposables in the CompositeDisposable
        disposables.dispose();

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
