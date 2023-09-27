package com.andy.rxjava.p4_reactive_operators.p1_combining;

import io.reactivex.rxjava3.core.Observable;

/**
 * Demonstrates the usage of RxJava's concatMap() operator.
 *
 * <p>
 * This example takes a source observable that emits strings and uses concatMap()
 * to split each string into individual characters. The characters are then emitted
 * one at a time, maintaining the order of the source emissions.
 * </p>
 */
public class ConcatMapExample {
    public static void main(String[] args) {
        Observable<String> source =
                Observable.just("Alpha", "Beta", "Gamma");

        source.concatMap(s -> Observable.fromArray(s.split("")))
                .subscribe(System.out::println);
    }
}
