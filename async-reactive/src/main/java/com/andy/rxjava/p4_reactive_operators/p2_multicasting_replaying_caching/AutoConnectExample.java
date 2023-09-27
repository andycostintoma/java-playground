package com.andy.rxjava.p4_reactive_operators.p2_multicasting_replaying_caching;

import io.reactivex.rxjava3.core.Observable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates the usage of the {@code autoConnect()} operator on ConnectableObservable.
 *
 * <p>The {@code autoConnect(int numberOfSubscribers)} operator returns an {@link Observable} that
 * automatically calls {@code connect()} after the specified number of observers have subscribed.
 * This simplifies the process of making a ConnectableObservable hot and starting emissions after a certain
 * number of subscribers are present.</p>
 *
 * <p>This example showcases how to use {@code autoConnect(2)} to ensure that emissions start when there
 * are two subscribers. It also demonstrates that {@code autoConnect()} keeps the subscription to the source
 * even after all observers have finished or been disposed of, allowing latecomers to miss previous emissions.</p>
 *
 * <p>Additionally, an example with {@code autoConnect()} without arguments is provided, which starts
 * firing emissions immediately upon the first subscription, allowing subsequent subscribers to miss earlier
 * emissions.</p>
 */
public class AutoConnectExample {
    public static void main(String[] args) {
        // Example with autoConnect(2)
        Observable<Integer> rInts =
            Observable.range(1, 3).map(i -> randomInt()).publish().autoConnect(2);

        // Observer 1 - prints each random integer
        rInts.subscribe(i -> System.out.println("Observer 1: " + i));

        // Observer 2 - sums the random integers, then prints
        rInts.reduce(0, (total, next) -> total + next)
            .subscribe(i -> System.out.println("Observer 2: " + i));

        // Observer 3 - receives nothing
        rInts.subscribe(i -> System.out.println("Observer 3: " + i));

        // Example with autoConnect() without arguments
        Observable<Long> ints =
            Observable.interval(1, TimeUnit.SECONDS).publish().autoConnect();

        // Observer 1
        ints.subscribe(i -> System.out.println("Observer 1: " + i));
        sleep(3000);

        // Observer 2
        ints.subscribe(i -> System.out.println("Observer 2: " + i));
        sleep(3000);

        // Example with autoConnect(0)
        Observable<Long> intsImmediate =
            Observable.interval(1, TimeUnit.SECONDS).publish().autoConnect(0);

        // Observers
        intsImmediate.subscribe(i -> System.out.println("Observer Immediate 1: " + i));
        sleep(3000);
    }

    /**
     * Generates a random integer between 0 and 100,000.
     *
     * @return A random integer.
     */
    public static int randomInt() {
        return ThreadLocalRandom.current().nextInt(100000);
    }

    /**
     * Sleeps the current thread for the specified duration.
     *
     * @param milliseconds The duration to sleep in milliseconds.
     */
    private static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
