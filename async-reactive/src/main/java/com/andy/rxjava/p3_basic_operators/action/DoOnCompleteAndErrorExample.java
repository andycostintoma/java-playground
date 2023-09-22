package com.andy.rxjava.p3_basic_operators.action;

import io.reactivex.rxjava3.core.Observable;

/**
 * Demonstrates the use of doOnComplete(), doOnError(), doOnTerminate(), and doAfterTerminate() operators in RxJava.
 * - Allows performing actions on onComplete and onError events.
 */
public class DoOnCompleteAndErrorExample {

    public static void main(String[] args) {
        // Example using doOnComplete() to perform an action on onComplete event
        Observable.just("Alpha", "Beta", "Gamma")
                .doOnComplete(() -> System.out.println("doOnComplete() - Source is done emitting!"))
                .map(String::length)
                .subscribe(i -> System.out.println("doOnComplete() - Received: " + i));

        // Example using doOnError() to perform an action on onError event
        Observable.just(5, 2, 4, 0, 3, 2, 8)
                .doOnError(e -> System.out.println("doOnError() - Source failed!"))
                .map(i -> divideTenBy(i))
                .doOnError(e -> System.out.println("doOnError() - Division failed!"))
                .subscribe(i -> System.out.println("doOnError() - RECEIVED: " + i),
                        e -> System.out.println("doOnError() - RECEIVED ERROR: " + e));

        // Example using doOnTerminate() to perform an action before onComplete or onError
        Observable.just("Alpha", "Beta", "Gamma")
                .doOnTerminate(() -> System.out.println("doOnTerminate() - Observable terminated!"))
                .map(String::length)
                .subscribe(i -> System.out.println("doOnTerminate() - Received: " + i));

        // Example using doAfterTerminate() to perform an action after onComplete or onError
        Observable.just("Alpha", "Beta", "Gamma")
                .doAfterTerminate(() -> System.out.println("doAfterTerminate() - Observable terminated!"))
                .map(String::length)
                .subscribe(i -> System.out.println("doAfterTerminate() - Received: " + i));
    }

    private static int divideTenBy(int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("/ by zero");
        }
        return 10 / divisor;
    }
}
