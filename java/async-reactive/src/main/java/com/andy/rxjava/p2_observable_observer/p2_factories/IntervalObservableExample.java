package com.andy.rxjava.p2_observable_observer.p2_factories;

import io.reactivex.rxjava3.core.Observable;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * This class demonstrates the usage of Observable.interval() to create an Observable that emits
 * consecutive long values at specified time intervals.
 */
public class IntervalObservableExample {

    /**
     * The main entry point of the program.
     *
     * @param args The command-line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // Create an Observable that emits consecutive long values every 1 second
        Observable<Long> seconds =
                Observable.interval(1, TimeUnit.SECONDS);

        // Observer 1: Prints the emitted values along with the current second
        seconds.subscribe(l -> System.out.println(LocalDateTime.now()
                .getSecond() + " " + l + " Mississippi"));

        // Sleep for 3 seconds to allow Observable to emit values
        sleep(3000);
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
