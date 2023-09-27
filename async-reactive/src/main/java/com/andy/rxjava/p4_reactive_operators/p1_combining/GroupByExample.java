package com.andy.rxjava.p4_reactive_operators.p1_combining;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.GroupedObservable;

/**
 * Demonstrates the usage of RxJava's groupBy() operator for grouping emissions by a specified key.
 */
public class GroupByExample {
    public static void main(String[] args) {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

        // Group emissions by the length of strings
        Observable<GroupedObservable<Integer, String>> byLengths =
                source.groupBy(s -> s.length());

        // For each GroupedObservable, collect emissions into lists
        byLengths.flatMapSingle(grp -> grp.toList())
                .subscribe(System.out::println);
    }
}
