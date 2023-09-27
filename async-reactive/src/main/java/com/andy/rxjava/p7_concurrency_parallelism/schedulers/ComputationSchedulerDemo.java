package com.andy.rxjava.p7_concurrency_parallelism.schedulers;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * This class demonstrates the usage of the computation Scheduler in RxJava.
 * <p>
 * The computation Scheduler, created using {@code Schedulers.computation()}, maintains a fixed number of threads
 * based on processor availability for the Java session. It is suitable for computational tasks that may utilize
 * processor cores to their fullest extent. The Scheduler ensures that there are no more worker threads than
 * available processor cores, making it efficient for such tasks.
 */
public class ComputationSchedulerDemo {

    public static void main(String[] args) throws InterruptedException {
        // Create an observable that emits strings.
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.computation()) // Use the computation Scheduler.
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
