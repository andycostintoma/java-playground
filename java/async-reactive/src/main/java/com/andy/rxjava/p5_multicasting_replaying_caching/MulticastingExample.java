package com.andy.rxjava.p5_multicasting_replaying_caching;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

/**
 * Demonstrates the usage of ConnectableObservable for multicasting emissions to multiple observers simultaneously.
 *
 * <p>
 * When working with cold Observables like the one created by Observable.range(),
 * emissions are regenerated for each subscribed Observer, resulting in separate streams of data.
 * </p>
 *
 * <p>
 * To consolidate emissions into a single stream and push each emission to all subscribed observers simultaneously,
 * we can use the publish() operator on an Observable, which returns a ConnectableObservable.
 * </p>
 *
 * <p>
 * In this example, we create a ConnectableObservable from a range of integers (1 to 3).
 * We subscribe two observers to the ConnectableObservable, and then we call connect() to start emitting the data.
 * Both observers receive the same emissions at the same time, effectively multicasting the data stream.
 * </p>
 *
 * <p>
 * Note that operators added before subscribe() can affect multicasting behavior.
 * Care should be taken when using operators to ensure the desired multicasting behavior.
 * </p>
 */
public class MulticastingExample {
    public static void main(String[] args) {
        // Create a ConnectableObservable from a range of integers (1 to 3)
        ConnectableObservable<Integer> ints = Observable.range(1, 3).publish();

        // Subscribe multiple observers to the ConnectableObservable
        ints.subscribe(i -> System.out.println("Observer One: " + i));
        ints.subscribe(i -> System.out.println("Observer Two: " + i));

        // Start emitting by connecting the ConnectableObservable
        ints.connect();
    }
}
