package com.andy.rxjava.p7_concurrency_parallelism;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * This class demonstrates RxJava concurrency concepts.
 */
public class RxJavaConcurrencyDemo {

    /**
     * Sleeps the current thread for the specified number of milliseconds.
     *
     * @param millis The number of milliseconds to sleep.
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Simulates an intense calculation that takes a random amount of time (0-3 seconds).
     *
     * @param value The value to be processed.
     * @param <T>   The type of the value.
     * @return The processed value.
     */
    private static <T> T intenseCalculation(T value) {
        sleep(ThreadLocalRandom.current().nextInt(3000));
        return value;
    }

    public static void main(String[] args) {

        // Example 1: Using Observable.interval()
        System.out.println("Example 1: Using Observable.interval()");
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MM:ss");
        Disposable disposable1 = Observable.interval(1, 1, TimeUnit.SECONDS)
                .map(i -> LocalDateTime.now().format(f) + " " + i + " Mississippi")
                .subscribe(System.out::println);
        sleep(5000);
        disposable1.dispose(); // Dispose the subscription to stop emitting and clean up resources.

        // Example 2: Using Observable.just() and Observable.range() with concurrency
        System.out.println("Example 2: Using Observable.just() and Observable.range() with concurrency");
        Disposable disposable2 = Observable.just("Alpha", "Beta", "Gamma")
                .map(s -> intenseCalculation(s))
                .subscribe(System.out::println);
        Disposable disposable3 = Observable.range(1, 3)
                .map(s -> intenseCalculation(s))
                .subscribe(System.out::println);
        disposable2.dispose(); // Dispose the subscriptions to stop emitting and clean up resources.
        disposable3.dispose();

        // Example 3: Using subscribeOn() to introduce concurrency
        System.out.println("Example 3: Using subscribeOn() to introduce concurrency");
        Disposable disposable4 = Observable.just("Alpha", "Beta", "Gamma")
                .subscribeOn(Schedulers.computation()) // Subscribe on a computation thread.
                .map(s -> intenseCalculation(s))
                .subscribe(System.out::println);
        Disposable disposable5 = Observable.range(1, 3)
                .subscribeOn(Schedulers.computation()) // Subscribe on a computation thread.
                .map(s -> intenseCalculation(s))
                .subscribe(System.out::println);
        sleep(10000);
        disposable4.dispose(); // Dispose the subscriptions to stop emitting and clean up resources.
        disposable5.dispose();

        // Example 4: Using zip() with observables on different threads
        System.out.println("Example 4: Using zip() with observables on different threads");
        Observable<String> source1 =
                Observable.just("Alpha", "Beta", "Gamma")
                        .subscribeOn(Schedulers.computation()) // Subscribe on a computation thread.
                        .map(s -> intenseCalculation(s));
        Observable<Integer> source2 =
                Observable.range(1, 3)
                        .subscribeOn(Schedulers.computation()) // Subscribe on a computation thread.
                        .map(s -> intenseCalculation(s));
        Observable.zip(source1, source2, (s, i) -> s + "-" + i)
                .subscribe(System.out::println);
        sleep(20000);
    }
}
