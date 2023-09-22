package com.andy.rxjava.p3_basic_operators.conditional;

import io.reactivex.rxjava3.core.Observable;

public class TakeAndSkipWhileExample {
    public static void main(String[] args) {
        // Simulated list of temperature values
        Observable<Integer> temperatures = Observable.just(25, 28, 32, 27, 30, 31, 29, 33, 26);

        System.out.println("=== Take Temperatures Below 30°C ===");
        temperatures
                .takeWhile(temp -> temp < 30) // Take temperatures below 30°C
                .subscribe(
                        temp -> System.out.println("Take: Temperature: " + temp + "°C"),
                        error -> System.err.println("Error: " + error),
                        () -> System.out.println("Take: Observation completed.")
                );

        System.out.println("=== Skip Temperatures Below 30°C ===");
        temperatures
                .skipWhile(temp -> temp < 30) // Skip temperatures below 30°C
                .subscribe(
                        temp -> System.out.println("Skip: Temperature: " + temp + "°C"),
                        error -> System.err.println("Error: " + error),
                        () -> System.out.println("Skip: Observation completed.")
                );
    }
}
