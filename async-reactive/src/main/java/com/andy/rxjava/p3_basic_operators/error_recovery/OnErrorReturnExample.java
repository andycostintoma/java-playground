package com.andy.rxjava.p3_basic_operators.error_recovery;

import io.reactivex.rxjava3.core.Observable;

/**
 * Demonstrates the use of onErrorReturnItem(), onErrorReturn(), and handling errors within the map() operator in RxJava.
 * - Handles exceptions gracefully with error recovery.
 */
public class OnErrorReturnExample {

    public static void main(String[] args) {
        // Example using onErrorReturnItem() to emit a default value when an exception occurs
        Observable.just(5, 2, 4, 0, 3)
                .map(OnErrorReturnExample::divideTenBy)
                .onErrorReturnItem(-1)
                .subscribe(i -> System.out.println("onErrorReturnItem() - RECEIVED: " + i),
                        e -> System.out.println("onErrorReturnItem() - RECEIVED ERROR: " + e));

        System.out.println();

        // Example using onErrorReturn() with a custom function to emit a value based on the exception
        Observable.just(5, 2, 4, 0, 3)
                .map(OnErrorReturnExample::divideTenBy)
                .onErrorReturn(e -> (e instanceof ArithmeticException) ? -1 : 0)
                .subscribe(i -> System.out.println("onErrorReturn() - RECEIVED: " + i),
                        e -> System.out.println("onErrorReturn() - RECEIVED ERROR: " + e));

        System.out.println();

        // Example handling errors within the map() operator and continuing emissions
        Observable.just(5, 2, 4, 0, 3)
                .map(i -> {
                    try {
                        return divideTenBy(i);
                    } catch (ArithmeticException e) {
                        return -1;
                    }
                })
                .subscribe(i -> System.out.println("Error Handling in map() - RECEIVED: " + i),
                        e -> System.out.println("Error Handling in map() - RECEIVED ERROR: " + e));
    }

    private static int divideTenBy(int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("/ by zero");
        }
        return 10 / divisor;
    }
}
