package com.andy.rxjava.p3_basic_operators.reducing;

import io.reactivex.rxjava3.core.Observable;

/**
 * Demonstrates the use of the reduce() operator to perform reductions on emitted values.
 */
public class ReduceExample {
    public static void main(String[] args) {
        // Example 1: Calculating the sum of emitted integers
        Observable.just(5, 3, 7)
                  .reduce(Integer::sum)
                  .subscribe(result -> System.out.println("Received: " + result));

        // Example 2: Concatenating integers as a comma-separated string
        Observable.just(5, 3, 7)
                  .reduce("", (total, i) -> total + (total.isEmpty() ? "" : ", ") + i)
                  .subscribe(result -> System.out.println("Received: " + result));
    }
}
