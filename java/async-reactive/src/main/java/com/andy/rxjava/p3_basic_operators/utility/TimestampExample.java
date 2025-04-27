package com.andy.rxjava.p3_basic_operators.utility;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * Demonstrates the use of the timestamp() operator in RxJava.
 * - Attaches a timestamp to each item emitted by the Observable.
 */
public class TimestampExample {

    public static void main(String[] args) throws InterruptedException {
        Observable<String> source = Observable.just("One", "Two", "Three");

        source
                .timestamp(TimeUnit.SECONDS) // Attach a timestamp in seconds
                .observeOn(Schedulers.computation()) // Switch to computation scheduler
                .subscribe(
                        timedItem -> {
                            long timestamp = timedItem.time();
                            TimeUnit unit = timedItem.unit();
                            String value = timedItem.value();

                            System.out.println("Received: " + timestamp + " " + unit + " " + value);
                        }
                );

        // Sleep to allow time for asynchronous processing
        Thread.sleep(2000);
    }
}
