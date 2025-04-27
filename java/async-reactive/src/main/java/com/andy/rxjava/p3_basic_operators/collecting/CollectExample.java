package com.andy.rxjava.p3_basic_operators.collecting;

import io.reactivex.rxjava3.core.Observable;
import java.util.HashSet;

/**
 * Demonstrates the use of the collect() operator in RxJava.
 * - Collects items into different types of collections.
 */
public class CollectExample {

    public static void main(String[] args) {
        // Example using HashSet to collect String values
        Observable.just("Alpha", "Beta", "Gamma", "Beta")
                .collect(HashSet::new, HashSet::add)
                .subscribe(s -> System.out.println("Collect to HashSet - Received: " + s));


    }
}
