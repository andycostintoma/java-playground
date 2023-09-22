package com.andy.rxjava.p3_basic_operators.transforming;

import io.reactivex.rxjava3.core.Observable;

import java.util.Comparator;

/**
 * Demonstrates the use of the sorted() operator to sort emitted items in an observable.
 */
public class SortedExample {
    public static void main(String[] args) {
        // Example 1: Sorting in natural order
        Observable.just(6, 2, 5, 7, 1, 4, 9, 8, 3)
                  .sorted()
                  .subscribe(System.out::print);

        System.out.println();

        // Example 2: Sorting in reverse order using Comparator.reverseOrder()
        Observable.just(6, 2, 5, 7, 1, 4, 9, 8, 3)
                  .sorted(Comparator.reverseOrder())
                  .subscribe(System.out::print);

        System.out.println();

        // Example 3: Sorting strings by length
        Observable.just("Alpha", "Beta", "Gamma")
                  .sorted(Comparator.comparingInt(String::length))
                  .subscribe(System.out::println);
    }
}
