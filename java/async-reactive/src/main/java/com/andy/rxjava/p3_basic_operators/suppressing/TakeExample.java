package com.andy.rxjava.p3_basic_operators.suppressing;

import io.reactivex.rxjava3.core.Observable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates the use of the take() operator to limit the number of emissions.
 */
public class TakeExample {
    public static void main(String[] args) {
        // Example 1: Count-based take
        Observable.just("Alpha", "Beta", "Gamma")
                .take(2)
                .subscribe(s -> System.out.println("RECEIVED: " + s));

        // Example 2: Time-based take
        DateTimeFormatter f = DateTimeFormatter.ofPattern("ss:SSS");
        System.out.println(LocalDateTime.now().format(f));
        Observable.interval(300, TimeUnit.MILLISECONDS)
                .take(2, TimeUnit.SECONDS)
                .subscribe(i -> System.out.println(LocalDateTime.now()
                        .format(f) + " RECEIVED: " + i));

        sleep(5000);
    }

    // Sleep function to delay the main thread for demonstration purposes
    private static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
