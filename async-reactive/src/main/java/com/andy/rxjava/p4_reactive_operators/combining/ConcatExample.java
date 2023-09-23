package com.andy.rxjava.p4_reactive_operators.combining;

import io.reactivex.rxjava3.core.Observable;
import java.util.concurrent.TimeUnit;

public class ConcatExample {
    public static void main(String[] args) {
        // Example 1: Using Observable.concat() with two source observables
        Observable<String> src1 = Observable.just("Alpha", "Beta");
        Observable<String> src2 = Observable.just("Zeta", "Eta");
        Observable.concat(src1, src2)
                .subscribe(i -> System.out.println("RECEIVED: " + i));

        // Example 2: Using concatWith() operator with two source observables
        Observable<String> src3 = Observable.just("Gamma", "Delta");
        src3.concatWith(src2)
                .subscribe(i -> System.out.println("RECEIVED: " + i));

        // Example 3: Using Observable.concat() with an infinite observable
        Observable<String> finiteSrc = Observable.interval(1, TimeUnit.SECONDS)
                .take(2)
                .map(l -> l + 1)
                .map(l -> "Source1: " + l + " seconds");
        Observable<String> infiniteSrc = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l -> (l + 1) * 300)
                .map(l -> "Source2: " + l + " milliseconds");
        Observable.concat(finiteSrc, infiniteSrc)
                .subscribe(i -> System.out.println("RECEIVED: " + i));

        // Keep the application alive for 5 seconds
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
