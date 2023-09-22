package com.andy.rxjava.p3_basic_operators.error_recovery;

import io.reactivex.rxjava3.core.Observable;

/**
 * Demonstrates the use of the onErrorResumeWith() operator in RxJava.
 * - Handles exceptions gracefully with the ability to emit multiple values.
 */
public class OnErrorResumeWithExample {

    public static void main(String[] args) {
        // Example using onErrorResumeWith() to emit multiple values in the event of an error
        Observable.just(5, 2, 4, 0, 3)
                .map(i -> divideTenBy(i))
                .onErrorResumeWith(Observable.just(-1).repeat(3))
                .subscribe(i -> System.out.println("onErrorResumeWith() - RECEIVED: " + i),
                        e -> System.out.println("onErrorResumeWith() - RECEIVED ERROR: " + e));

        System.out.println();

        // Example using onErrorResumeWith() to quietly stop emissions in the event of an error
        Observable.just(5, 2, 4, 0, 3)
                .map(i -> divideTenBy(i))
                .onErrorResumeWith(Observable.empty())
                .subscribe(i -> System.out.println("onErrorResumeWith() - RECEIVED: " + i),
                        e -> System.out.println("onErrorResumeWith() - RECEIVED ERROR: " + e));

        System.out.println();

        // Example using onErrorResumeWith() with a custom function to produce an Observable dynamically
        Observable.just(5, 2, 4, 0, 3)
                .map(i -> divideTenBy(i))
                .onErrorResumeNext((Throwable e) ->
                        Observable.just(-1).repeat(3))
                .subscribe(i -> System.out.println("onErrorResumeNext() - RECEIVED: " + i),
                        e -> System.out.println("onErrorResumeNext() - RECEIVED ERROR: " + e));
    }

    private static int divideTenBy(int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("/ by zero");
        }
        return 10 / divisor;
    }
}
