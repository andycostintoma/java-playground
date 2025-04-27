package com.andy.rxjava.p3_basic_operators.transforming;

import io.reactivex.rxjava3.core.Observable;

import java.util.Arrays;

/**
 * Demonstrates the use of startWithItem() and startWithArray() operators to insert values before emitting other values.
 */
public class StartWithExample {
    public static void main(String[] args) {
        // Example 1: Using startWithItem()
        Observable<String> menu =
                Observable.just("Coffee", "Tea", "Espresso", "Latte");

        // Insert "COFFEE SHOP MENU" before emitting menu items
        menu.startWithItem("COFFEE SHOP MENU")
            .subscribe(System.out::println);

        // Example 2: Using startWithArray()
        Observable<String> menu2 =
                Observable.just("Coffee", "Tea", "Espresso", "Latte");

        // Insert both header and divider before emitting menu items
        menu2.startWithArray("COFFEE SHOP MENU", "----------------")
             .subscribe(System.out::println);

        // Example 3: Using startWithIterable()
        Observable<String> menu3 =
                Observable.just("Coffee", "Tea", "Espresso", "Latte");

        // Insert values from an iterable before emitting menu items
        menu3.startWithIterable(Arrays.asList("COFFEE SHOP MENU", "----------------"))
             .subscribe(System.out::println);
    }
}
