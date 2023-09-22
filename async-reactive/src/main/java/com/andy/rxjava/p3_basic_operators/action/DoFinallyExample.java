package com.andy.rxjava.p3_basic_operators.action;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates the use of the doFinally() operator in RxJava.
 * - Executes an action when onComplete(), onError(), or disposal happens.
 * - Ensures the action is executed exactly once per subscription.
 */
public class DoFinallyExample {

    public static void main(String[] args) {
        Disposable disp = Observable.interval(1, TimeUnit.SECONDS)
                .doOnSubscribe(d -> System.out.println("Subscribing!"))
                .doOnDispose(() -> System.out.println("Disposing!"))
                .doFinally(() -> System.out.println("doFinally!"))
                .doAfterTerminate(() -> System.out.println("doAfterTerminate!"))
                .subscribe(i -> System.out.println("RECEIVED: " + i));

        sleep(3000);
        disp.dispose();
        sleep(3000);
    }

    // Helper method for sleeping
    private static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
