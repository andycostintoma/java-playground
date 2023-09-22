package com.andy.rxjava.p3_basic_operators.action;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.concurrent.TimeUnit;

/**
 * Demonstrates the use of doOnSubscribe() and doOnDispose() operators in RxJava.
 * - Allows performing actions when subscription and disposal occur.
 */
public class DoOnSubscribeAndDisposeExample {

    public static void main(String[] args) {
        // Example showing doOnSubscribe() and doOnDispose() operators
        Disposable disposable = Observable.interval(1, TimeUnit.SECONDS)
                .doOnSubscribe(d -> System.out.println("Subscribing!"))
                .doOnDispose(() -> System.out.println("Disposing!"))
                .subscribe(i -> System.out.println("RECEIVED: " + i));

        // Delay for 3 seconds to allow subscription and some emissions
        sleep(3000);

        // Dispose the subscription
        disposable.dispose();

        // Delay for another 3 seconds to observe disposal
        sleep(3000);
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
