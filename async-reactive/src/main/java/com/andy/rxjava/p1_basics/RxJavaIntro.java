package com.andy.rxjava.p1_basics;

import io.reactivex.rxjava3.core.Observable;
import java.util.concurrent.TimeUnit;

/**
 * This class demonstrates the basic usage of RxJava with Observable and Observer.
 */
public class RxJavaIntro {

    /**
     * The main entry point of the program.
     *
     * @param args The command-line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // Create an Observable<String> that emits three string objects.
        Observable<String> myStrings = Observable.just("Alpha", "Beta", "Gamma");

        // Subscribe to the Observable, apply a transformation, and specify what to do with each emitted item.
        myStrings.map(String::length)
                .subscribe(length -> System.out.println("String Length: " + length));

        // Create an Observable<Long> that emits consecutive Longs at 1-second intervals.
        Observable<Long> secondIntervals = Observable.interval(1, TimeUnit.SECONDS);

        // Subscribe to the interval Observable and print each emitted Long.
        secondIntervals.subscribe(time -> System.out.println("Time Elapsed (s): " + time));

        /* Hold the main thread for 5 seconds
           to allow the interval Observable to emit values. */
        sleep(5000);
    }

    /**
     * Pauses the current thread for the specified number of milliseconds.
     *
     * @param millis The number of milliseconds to pause the thread.
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
