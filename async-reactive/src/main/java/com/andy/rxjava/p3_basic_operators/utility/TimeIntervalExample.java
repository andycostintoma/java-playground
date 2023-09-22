package com.andy.rxjava.p3_basic_operators.utility;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * Demonstrates the use of the timeInterval() operator in RxJava.
 * - Emits time intervals between consecutive emissions of a source Observable.
 */
public class TimeIntervalExample {

    public static void main(String[] args) throws InterruptedException {
        Observable<Long> source = Observable.interval(2, TimeUnit.SECONDS)
                .doOnNext(i -> System.out.println("Emitted: " + i))
                .take(3);

        source
            .timeInterval(TimeUnit.SECONDS) // Emit time intervals in seconds
            .observeOn(Schedulers.computation()) // Switch to computation scheduler
            .subscribe(
                timedInterval -> {
                    long interval = timedInterval.time();
                    TimeUnit unit = timedInterval.unit();
                    long originalValue = timedInterval.value();

                    System.out.println("Received: " + interval + " " + unit + " " + originalValue);
                }
            );

        // Sleep to allow time for asynchronous processing
        Thread.sleep(7000);
    }
}
