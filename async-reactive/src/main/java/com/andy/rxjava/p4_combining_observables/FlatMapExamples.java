package com.andy.rxjava.p4_combining_observables;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

/**
 * This Java program demonstrates the usage of the flatMap() operator in RxJava.
 * It covers various examples of using flatMap() to transform and merge emissions.
 *
 * @author Your Name
 */
public class FlatMapExamples {
    public static void main(String[] args) throws InterruptedException {
        // Example 1: Mapping one emission to many emissions
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma");
        source1.flatMap(s -> Observable.fromArray(s.split("")))
                .subscribe(System.out::println);

        // Example 2: Mapping and filtering emissions
        Observable<String> source2 = Observable.just("521934/2342/FOXTROT", "21962/12112/TANGO/78886");
        source2.flatMap(s -> Observable.fromArray(s.split("/")))
                .filter(s -> s.matches("[0-9]+"))
                .map(Integer::valueOf)
                .subscribe(System.out::println);

        // Example 3: Mapping to interval Observables and merging
        Observable<Integer> source3 = Observable.just(2, 3, 10, 7);
        source3.flatMap(i -> Observable.interval(i, i + 1, TimeUnit.SECONDS)
                        .map(l -> i + "s interval: " + l + " seconds elapsed"))
                .subscribe(System.out::println);

        // Example 4: Handling zero value to avoid infinite interval
        Observable<Integer> source4 = Observable.just(2, 0, 3, 10, 7);
        source4.flatMap(i -> {
            if (i == 0) {
                return Observable.empty();
            } else {
                return Observable.interval(i, i + 1, TimeUnit.SECONDS)
                        .map(l -> i + "s interval: " + l + " seconds elapsed");
            }
        }).subscribe(System.out::println);


        // Keep the program running for a while to allow async processing
        Thread.sleep(5000);
    }
}
