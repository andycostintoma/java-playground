package com.andy.rxjava.p4_combining_observables;

import io.reactivex.rxjava3.core.Observable;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This Java program demonstrates the usage of Observable.merge(), mergeWith(),
 * and merging multiple Observable sources. It covers the following concepts:
 *
 * 1. Merging two Observable sources using Observable.merge() and mergeWith().
 * 2. Merging multiple Observable sources using Observable.mergeArray().
 * 3. Merging multiple Observable sources using Observable.merge() with a List.
 * 4. Merging Observable sources with different emission rates.
 *
 * @author Your Name
 */
public class MergeExamples {
    public static void main(String[] args) {
        // Example 1: Using Observable.merge()
        Observable<String> src1 = Observable.just("Alpha", "Beta");
        Observable<String> src2 = Observable.just("Zeta", "Eta");

        Observable.merge(src1, src2)
                .subscribe(i -> System.out.println("Example 1: RECEIVED: " + i));

        // Example 2: Using mergeWith()
        src1.mergeWith(src2)
                .subscribe(i -> System.out.println("Example 2: RECEIVED: " + i));

        // Example 3: Merging Multiple Observables with Observable.mergeArray()
        Observable<String> src3 = Observable.just("Gamma", "Delta");
        Observable<String> src4 = Observable.just("Epsilon", "Zeta");
        Observable<String> src5 = Observable.just("Eta", "Theta");
        Observable<String> src6 = Observable.just("Iota", "Kappa");

        Observable.mergeArray(src3, src4, src5, src6)
                .subscribe(i -> System.out.println("Example 3: RECEIVED: " + i));

        // Example 4: Merging Multiple Observables with Observable.merge()
        List<Observable<String>> sources = Arrays.asList(src1, src2, src3, src4, src5);
        Observable.merge(sources)
                .subscribe(i -> System.out.println("Example 4: RECEIVED: " + i));

        // Example 5: Merging Observable Sources with Different Emission Rates
        Observable<String> src7 = Observable.interval(1, TimeUnit.SECONDS)
                .map(l -> l + 1)
                .map(l -> "Source1: " + l + " seconds");

        Observable<String> src8 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l -> (l + 1) * 300)
                .map(l -> "Source2: " + l + " milliseconds");

        Observable.merge(src7, src8)
                .subscribe(System.out::println);

        // Keep the program running for 5 seconds
        sleep(5000);
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
