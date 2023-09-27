package com.andy.rxjava.p4_reactive_operators.p1_combining;

import io.reactivex.rxjava3.core.Observable;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates the usage of RxJava's Observable.combineLatest() operator for combining emissions from multiple sources.
 *
 * <p>
 * The Observable.combineLatest() factory combines the most recent emissions from multiple sources into a single emission.
 * For every emission from any of the sources, it pairs that emission with the latest emission from all other sources.
 * </p>
 *
 * <p>
 * In this example, two observables are used: source1 and source2. source1 emits values every 300 milliseconds,
 * while source2 emits values every second. The combineLatest() operator combines emissions from both sources.
 * When one source emits a value, it immediately pairs with the latest value from the other source.
 * </p>
 *
 * <p>
 * For instance, if source1 emits "2" and source2 emits "0," the resulting emission will be "SOURCE 1: 2 SOURCE 2: 0."
 * Subsequent emissions will follow the same pattern, pairing the latest values from both sources.
 * </p>
 *
 * <p>
 * The application will run for 3 seconds, allowing observations of the paired emissions.
 * </p>
 */
public class CombineLatestExample {
    public static void main(String[] args) {
        // Observable emitting every 300 milliseconds
        Observable<Long> source1 =
                Observable.interval(300, TimeUnit.MILLISECONDS);

        // Observable emitting every second
        Observable<Long> source2 =
                Observable.interval(1, TimeUnit.SECONDS);

        // Combine emissions from source1 and source2
        Observable.combineLatest(source1, source2, (l1, l2) ->
                        "SOURCE 1: " + l1 + " SOURCE 2: " + l2)
                .subscribe(System.out::println);

        // Keep the application alive for 3 seconds
        sleep(3000);
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
