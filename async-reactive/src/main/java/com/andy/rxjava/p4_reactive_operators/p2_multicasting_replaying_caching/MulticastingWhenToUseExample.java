package com.andy.rxjava.p4_reactive_operators.p2_multicasting_replaying_caching;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Demonstrates when to use multicasting with ConnectableObservable.
 *
 * <p>Multicasting is helpful when you need to send the same data to several observers,
 * especially when the emitted data has to be processed in the same way for each observer.
 * It is useful for performance optimization, reducing memory and CPU usage, or when business logic requires
 * pushing the same emissions to all observers.</p>
 *
 * <p>It's important to make cold observables multicast only when there are multiple observers receiving
 * the same data simultaneously. Be cautious when using multicasting with hot observables (e.g., UI events),
 * as it can cause redundant work for single observers.</p>
 *
 * <p>To determine when to multicast, find the proxy point where you can consolidate upstream operations
 * for common operations among observers, and let the streams fork into separate operations downstream
 * when needed. For example, if one observer prints random integers and another finds their sum, the
 * stream should fork into two separate streams at the point where their operations diverge.</p>
 */
public class MulticastingWhenToUseExample {
    public static void main(String[] args) {
        // Create a ConnectableObservable
        ConnectableObservable<Integer> rInts =
              Observable.range(1, 3).map(i -> randomInt()).publish();

        // Observer 1 - Print each random integer
        rInts.subscribe(i -> System.out.println("Observer 1: " + i));

        // Observer 2 - Sum the random integers, then print
        rInts.reduce(0, (total, next) -> total + next)
             .subscribe(i -> System.out.println("Observer 2: " + i));

        // Connect to start emissions
        rInts.connect();
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
