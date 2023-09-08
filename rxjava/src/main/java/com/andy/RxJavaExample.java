package com.andy; /**
 * <p>This example demonstrates the basics of using RxJava, focusing on Observables, Observers, and operators.</p>
 * 
 * <p>RxJava allows us to work with asynchronous data streams, such as emitting data or events over time. 
 * The core type, Observable, represents such streams.</p>
 * 
 * <p>In this example, we create an Observable of strings, apply the 'map' operator to transform each string into its length, 
 * and then subscribe to observe the resulting stream of lengths.</p>
 */

import io.reactivex.rxjava3.core.Observable;
import java.util.concurrent.TimeUnit;

public class RxJavaExample {
    public static void main(String[] args) {
        // Create an Observable that emits strings
        Observable<String> myStrings = Observable.just("Alpha", "Beta", "Gamma");

        // Subscribe to the Observable and print each emitted item's length
        myStrings.map(s -> s.length())
                .subscribe(length -> System.out.println("Length: " + length));

        // Create an Observable that emits consecutive Long values every second
        Observable<Long> secondIntervals = Observable.interval(1, TimeUnit.SECONDS);

        // Subscribe to the secondIntervals Observable and print each emitted value
        secondIntervals.subscribe(value -> System.out.println("Value: " + value));

        // Hold the main thread for a few seconds to allow Observable.interval to emit values
        sleep(5000);
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
