package com.andy.rxjava.p3_basic_operators.collecting;

import io.reactivex.rxjava3.core.Observable;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Demonstrates the use of the toList() operator to collect emitted items into a List.
 */
public class ToListExample {
    public static void main(String[] args) {
        // Collecting String values into a List<String>
        Observable.just("Alpha", "Beta", "Gamma")
                .toList()
                .subscribe(list -> System.out.println("Received: " + list));

        // Providing a capacityHint for optimization
        Observable.range(1, 1000)
                .toList(1000)
                .subscribe(list -> System.out.println("Received: " + list));

        // Using a different List implementation (CopyOnWriteArrayList)
        Observable.just("Beta", "Gamma", "Alpha")
                .toList(CopyOnWriteArrayList::new)
                .subscribe(list -> System.out.println("Received: " + list));
    }
}
