package com.andy.rxjava.p4_reactive_operators.p1_combining;

import io.reactivex.rxjava3.core.Observable;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates the usage of RxJava's withLatestFrom() operator for combining emissions with the latest values from other observables.
 */
public class WithLatestFromExample {
    public static void main(String[] args) {
        // Observable emitting every 300 milliseconds
        Observable<Long> source1 = Observable.interval(300, TimeUnit.MILLISECONDS);

        // Observable emitting every second
        Observable<Long> source2 = Observable.interval(1, TimeUnit.SECONDS);

        // Combine emissions from source2 with the latest emission from source1
        source2.withLatestFrom(source1, (l1, l2) ->
            "SOURCE 2: " + l1 + " SOURCE 1: " + l2)
               .subscribe(System.out::println);

        // Keep the application alive for 3 seconds
        sleep(3000);
    }

    // Helper method to sleep the current thread
    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
