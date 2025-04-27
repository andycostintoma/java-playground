package com.andy.rxjava.p7_concurrency_parallelism;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * This class demonstrates keeping an application alive using blocking operators in RxJava.
 */
public class KeepAliveDemo {

    /**
     * Simulates an intense calculation that takes a random amount of time (0-3 seconds).
     *
     * @param value The value to be processed.
     * @param <T>   The type of the value.
     * @return The processed value.
     */
    private static <T> T intenseCalculation(T value) {
        try {
            // Simulate intense calculation by sleeping for a random time.
            Thread.sleep((long) (Math.random() * 3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static void main(String[] args) {
        // Example 1: Using Thread.sleep() to keep the main thread alive
//        System.out.println("Example 1: Using Thread.sleep() to keep the main thread alive");
//        Observable.interval(1, TimeUnit.SECONDS)
//                .map(l -> intenseCalculation(l))
//                .subscribe(System.out::println);
//        sleep(Long.MAX_VALUE);

        // Example 2: Using blockingSubscribe() to keep the application alive
        System.out.println("Example 2: Using blockingSubscribe() to keep the application alive");
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.computation())
                .map(KeepAliveDemo::intenseCalculation)
                .blockingSubscribe(
                        System.out::println,
                        Throwable::printStackTrace,
                        () -> System.out.println("Done!")
                );
    }

    /**
     * Sleeps the current thread for the specified number of milliseconds.
     *
     * @param millis The number of milliseconds to sleep.
     */
    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
