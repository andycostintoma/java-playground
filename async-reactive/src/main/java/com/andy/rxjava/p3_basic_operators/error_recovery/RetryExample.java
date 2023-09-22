package com.andy.rxjava.p3_basic_operators.error_recovery;

import io.reactivex.rxjava3.core.Observable;

/**
 * Demonstrates the use of the retry() operator in RxJava.
 * - Retries the observable upon encountering an error.
 */
public class RetryExample {

    public static void main(String[] args) {
        // Example using retry() without parameters (infinite retries)
/*        Observable.just(5, 2, 4, 0, 3)
                .map(i -> divideTenBy(i))
                .retry()
                .subscribe(i -> System.out.println("retry() - RECEIVED: " + i),
                        e -> System.out.println("retry() - RECEIVED ERROR: " + e));
                        */

        // Example using retry() with a fixed number of retries
        Observable.just(5, 2, 4, 0, 3, 2, 8)
                .map(i -> divideTenBy(i))
                .retry(2)
                .subscribe(i -> System.out.println("retry(2) - RECEIVED: " + i),
                        e -> System.out.println("retry(2) - RECEIVED ERROR: " + e));

        // Example using retry() with a custom retry condition
  /*      Observable.just(5, 2, 4, 0, 3, 2, 8)
                .map(i -> divideTenBy(i))
                .retry(e -> e instanceof ArithmeticException)
                .subscribe(i -> System.out.println("retry(condition) - RECEIVED: " + i),
                        e -> System.out.println("retry(condition) - RECEIVED ERROR: " + e));
                        */

        // Example using retryUntil() to retry until a condition is met
        Observable.just(5, 2, 4, 0, 3, 2, 8)
                .map(i -> divideTenBy(i))
                .retryUntil(() -> someCondition())
                .subscribe(i -> System.out.println("retryUntil() - RECEIVED: " + i),
                        e -> System.out.println("retryUntil() - RECEIVED ERROR: " + e));
    }

    private static int divideTenBy(int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("/ by zero");
        }
        return 10 / divisor;
    }

    private static boolean someCondition() {
        // Define your custom condition here
        // For this example, we'll return true after a certain number of retries
        retryCount++;
        return retryCount >= 2;
    }

    private static int retryCount = 0;
}
