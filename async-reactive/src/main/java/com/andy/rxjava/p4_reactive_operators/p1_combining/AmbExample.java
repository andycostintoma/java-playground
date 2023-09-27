package com.andy.rxjava.p4_reactive_operators.p1_combining;

import io.reactivex.rxjava3.core.Observable;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates the usage of RxJava's Observable.amb() factory and ambWith() operator.
 *
 * <p>
 * The Observable.amb() factory accepts an Iterable<Observable<T>> object as a parameter
 * and emits the values of the first Observable that emits, while the others are disposed of.
 * This is helpful when there are multiple sources of the same data or events, and you want
 * the fastest one to win.
 * </p>
 */
public class AmbExample {
    public static void main(String[] args) {
        // Emit every second
        Observable<String> src1 =
            Observable.interval(1, TimeUnit.SECONDS)
                      .take(2)
                      .map(l -> l + 1) // Emit elapsed seconds
                      .map(l -> "Source1: " + l + " seconds");

        // Emit every 300 milliseconds
        Observable<String> src2 =
            Observable.interval(300, TimeUnit.MILLISECONDS)
                      .map(l -> (l + 1) * 300) // Emit elapsed milliseconds
                      .map(l -> "Source2: " + l + " milliseconds");

        // Use Observable.amb() to choose the fastest source
        Observable.amb(Arrays.asList(src1, src2))
                  .subscribe(i -> System.out.println("RECEIVED: " + i));

        // Keep application alive for 5 seconds
        sleep(5000);
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
