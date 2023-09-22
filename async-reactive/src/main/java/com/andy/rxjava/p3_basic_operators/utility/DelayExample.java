package com.andy.rxjava.p3_basic_operators.utility;

import io.reactivex.rxjava3.core.Observable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates the use of the delay() operator in RxJava.
 * - Delays emissions by a specified time period.
 * - Optionally delays error notifications as well.
 */
public class DelayExample {

    public static void main(String[] args) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MM:ss");
        System.out.println(LocalDateTime.now().format(f));

        Observable.just("Alpha", "Beta", "Gamma")
                .delay(3, TimeUnit.SECONDS)
                .subscribe(s -> System.out.println(LocalDateTime.now()
                        .format(f) + " Received: " + s));

        // Sleep to keep the application alive for delayed emissions
        sleep(5000);
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
