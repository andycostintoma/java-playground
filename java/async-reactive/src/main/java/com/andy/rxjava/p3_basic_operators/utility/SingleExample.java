package com.andy.rxjava.p3_basic_operators.utility;

import io.reactivex.rxjava3.core.Observable;

/**
 * Demonstrates the use of the single() operator in RxJava.
 * - Returns a Single that emits the item emitted by the Observable.
 * - Throws an exception if the Observable emits more than one item.
 * - Emits the default item if the Observable emits no items.
 */
public class SingleExample {

    public static void main(String[] args) {
        // Example 1: Observable with a single item
        Observable.just("One")
                .single("Four") // Returns the single item, or "Four" if no items are emitted
                .subscribe(i -> System.out.println("Received: " + i));

        // Example 2: Observable with filtering
        Observable.just("One", "Two", "Three")
                .filter(s -> s.contains("z")) // Filter items that contain "z"
                .single("Four") // Returns "Four" as no items match the filter
                .subscribe(i -> System.out.println("Received: " + i));
    }
}
