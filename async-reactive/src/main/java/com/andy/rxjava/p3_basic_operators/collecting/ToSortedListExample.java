package com.andy.rxjava.p3_basic_operators.collecting;

import io.reactivex.rxjava3.core.Observable;

import java.util.Comparator;

/**
 * Demonstrates the use of the toSortedList() operator to collect emitted items into a sorted List.
 */
public class ToSortedListExample {
    public static void main(String[] args) {
        // Sorting String values in natural order
        Observable.just("Beta", "Gamma", "Alpha")
                  .toSortedList()
                  .subscribe(sortedList -> System.out.println("Received: " + sortedList));

        // Sorting integers in descending order using a custom Comparator
        Observable.just(5, 3, 7, 1, 8)
                  .toSortedList(Comparator.reverseOrder())
                  .subscribe(sortedList -> System.out.println("Received: " + sortedList));

        // Providing an initial capacityHint for optimization
        Observable.just("Zeta", "Delta", "Theta")
                  .toSortedList()
                  .subscribe(sortedList -> System.out.println("Received: " + sortedList));
    }
}
