package com.andy.rxjava.p3_basic_operators.suppressing;

import io.reactivex.rxjava3.core.Observable;

/**
 * Demonstrates the use of the skip() operator to ignore a specified number of emissions.
 */
public class SkipExample {
    public static void main(String[] args) {
        // Use the skip() operator to skip the first 90 emissions
        Observable.range(1, 100)
                .skip(90)
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }
}
