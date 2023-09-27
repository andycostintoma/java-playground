package com.andy.rxjava.p7_concurrency_parallelism.schedulers;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * This class demonstrates the usage of the single Scheduler in RxJava.
 * <p>
 * The {@code Schedulers.single()} factory method creates a Scheduler backed by a single-threaded implementation
 * appropriate for event looping. It is designed for running tasks sequentially on a single thread. This can be
 * useful for isolating fragile, non-thread-safe operations to a single thread and ensuring that they are executed
 * sequentially. The single-threaded nature of this Scheduler makes it suitable for event-driven programming and
 * scenarios where tasks need to be performed in order.
 */
public class SingleSchedulerDemo {

    public static void main(String[] args) throws InterruptedException {
        // Create an observable that emits strings.
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.single()) // Use the single Scheduler.
                .map(s -> intenseCalculation(s))
                .subscribe(
                        result -> System.out.println("Received: " + result),
                        throwable -> throwable.printStackTrace(),
                        () -> System.out.println("Done!")
                );

        Thread.sleep(10000);
    }

    /**
     * Simulates an intense calculation that takes a random amount of time (0-3 seconds).
     *
     * @param value The value to be processed.
     * @return The processed value.
     */
    private static String intenseCalculation(String value) {
        try {
            // Simulate intense calculation by sleeping for a random time.
            Thread.sleep((long) (Math.random() * 3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value;
    }
}
