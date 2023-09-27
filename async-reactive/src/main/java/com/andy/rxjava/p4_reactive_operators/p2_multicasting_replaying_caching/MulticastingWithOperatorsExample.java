package com.andy.rxjava.p4_reactive_operators.p2_multicasting_replaying_caching;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Demonstrates multicasting within a chain of operators and explains how to achieve desired behavior.
 *
 * <p>In this example, we use {@link Observable#range(int, int)} to emit the numbers 1 through 3
 * and then map each emitted value to a random integer between 0 and 100,000.
 * Since these random values are non-deterministic and different for each subscription,
 * two separate observers are expected to receive different random integers.</p>
 *
 * <p>However, even after using {@link ConnectableObservable#publish()} to create a ConnectableObservable
 * and applying the {@link Observable#map} operator, each observer still receives separate random integers.
 * This is because the multicast operation is performed before the {@code map()} operator.</p>
 *
 * <p>To ensure both observers receive the same three random integers, we need to call {@code publish()} after {@code map()}.
 * This example demonstrates the correct sequence of operations to achieve the desired multicasting behavior.</p>
 */
public class MulticastingWithOperatorsExample {
    public static void main(String[] args) {
        // Case 1: Each observer gets separate random integers
        Observable<Integer> intsCase1 = Observable.range(1, 3)
                .map(i -> randomInt());
        intsCase1.subscribe(i -> System.out.println("Observer 1 (Case 1): " + i));
        intsCase1.subscribe(i -> System.out.println("Observer 2 (Case 1): " + i));

        // Case 2: Multicasting with publish() before map()
        ConnectableObservable<Integer> rIntsCase2 = Observable.range(1, 3)
                .publish();
        Observable<Integer> rIntsMappedCase2 = rIntsCase2.map(i -> randomInt());
        rIntsMappedCase2.subscribe(i -> System.out.println("Observer 1 (Case 2): " + i));
        rIntsMappedCase2.subscribe(i -> System.out.println("Observer 2 (Case 2): " + i));
        rIntsCase2.connect();

        // Case 3: Multicasting with publish() after map()
        ConnectableObservable<Integer> rIntsCase3 = Observable.range(1, 3)
                .map(i -> randomInt())
                .publish();
        rIntsCase3.subscribe(i -> System.out.println("Observer 1 (Case 3): " + i));
        rIntsCase3.subscribe(i -> System.out.println("Observer 2 (Case 3): " + i));
        rIntsCase3.connect();
    }

    /**
     * Generates a random integer between 0 and 100,000.
     *
     * @return A random integer.
     */
    public static int randomInt() {
        return ThreadLocalRandom.current().nextInt(100000);
    }
}
