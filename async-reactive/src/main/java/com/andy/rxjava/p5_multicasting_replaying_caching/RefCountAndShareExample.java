package com.andy.rxjava.p5_multicasting_replaying_caching;

import io.reactivex.rxjava3.core.Observable;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates the usage of the {@code refCount()} operator on ConnectableObservable.
 *
 * <p>The {@code refCount()} operator is similar to {@code autoConnect(1)}, but it has the ability
 * to dispose of itself and start over when there are no more subscribers. When a new subscription occurs,
 * it essentially starts a fresh sequence.</p>
 *
 * <p>This example showcases how {@code refCount()} behaves when used with {@link Observable#interval(long, TimeUnit)}
 * to emit values every second. Observers 1 and 2 take a limited number of emissions, and Observer 3
 * subscribes after the first two observers have completed their subscriptions. Note that Observer 3 starts
 * over with a fresh set of intervals.</p>
 *
 * <p>Additionally, the {@code share()} operator is introduced as an alias for {@code publish().refCount()},
 * providing a more concise way to achieve the same behavior.</p>
 */
public class RefCountAndShareExample {
    public static void main(String[] args) {
        // Example with refCount()
        Observable<Long> ints =
            Observable.interval(1, TimeUnit.SECONDS).publish().refCount();

        // Observer 1
        ints.take(5)
            .subscribe(l -> System.out.println("Observer 1: " + l));
        sleep(3000);

        // Observer 2
        ints.take(2)
            .subscribe(l -> System.out.println("Observer 2: " + l));
        sleep(3000);

        // There should be no more subscribers at this point

        // Observer 3
        ints.subscribe(l -> System.out.println("Observer 3: " + l));
        sleep(3000);

        // Example with share() (an alias for publish().refCount())
        Observable<Long> intsShared =
            Observable.interval(1, TimeUnit.SECONDS).share();

        // Observer 4
        intsShared.take(3)
            .subscribe(l -> System.out.println("Observer 4: " + l));
        sleep(3000);

        // Observer 5
        intsShared.take(2)
            .subscribe(l -> System.out.println("Observer 5: " + l));
        sleep(3000);

        // Observer 6
        intsShared.subscribe(l -> System.out.println("Observer 6: " + l));
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
