package com.andy.rxjava.p4_reactive_operators.p2_multicasting_replaying_caching;

import io.reactivex.rxjava3.core.Observable;

/**
 * Demonstrates the usage of the {@code cache()} operator to cache all emissions indefinitely for the long term.
 * The {@code cache()} operator subscribes to the source on the first downstream Observer that subscribes and
 * holds all values indefinitely, making it suitable for infinite Observables or large datasets.
 */
public class CacheExample {
    public static void main(String[] args) {
        // Using cache() to cache emissions indefinitely
        Observable<Integer> cachedRollingTotals =
                Observable.just(6, 2, 5, 7, 1, 4, 9, 8, 3)
                          .scan(0, (total, next) -> total + next)
                          .cache();

        // Subscribe to the cached Observable
        cachedRollingTotals.subscribe(System.out::println);

        // You can also use cacheWithInitialCapacity() to specify the initial cache capacity
        // Observable<Integer> cachedRollingTotalsWithCapacity =
        //         Observable.just(6, 2, 5, 7, 1, 4, 9, 8, 3)
        //                   .scan(0, (total, next) -> total + next)
        //                   .cacheWithInitialCapacity(9);
    }
}
