package com.andy.rxjava.p4_reactive_operators.p1_combining;

import io.reactivex.rxjava3.core.Observable;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates the usage of RxJava's Observable.zip() operator for zipping emissions from
 * multiple sources.
 */
public class ZipExample {
    public static void main(String[] args) {
        // Example 1: Zipping emissions from two sources
        Observable<String> src1 = Observable.just("Alpha", "Beta", "Gamma");
        Observable<Integer> src2 = Observable.range(1, 6);

        Observable.zip(src1, src2, (s, i) -> s + "-" + i)
                .subscribe(System.out::println);

        // Example 2: Zipping emissions with an interval to slow down emissions
        Observable<String> strings = Observable.just("Alpha", "Beta", "Gamma");
        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);

        Observable.zip(strings, seconds, (s, l) -> s)
                .subscribe(s -> System.out.println("Received " + s +
                        " at " + LocalTime.now()));

        // Keep application alive for 4 seconds
        sleep(4000);
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
