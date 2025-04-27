package com.andy.rxjava.p3_basic_operators.action;

import io.reactivex.rxjava3.core.Observable;

/**
 * Demonstrates the use of the doOnSuccess() operator in RxJava.
 * - Allows performing an action when a single success emission occurs.
 */
public class DoOnSuccessExample {

    public static void main(String[] args) {
        Observable.just(5, 3, 7)
                .reduce((total, next) -> total + next)
                .doOnSuccess(i -> System.out.println("Emitting: " + i))
                .subscribe(i -> System.out.println("Received: " + i));
    }
}
