package com.andy.rxjava.p7_concurrency_parallelism.schedulers;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * This class demonstrates the usage of the newThread Scheduler in RxJava.
 * <p>
 * The {@code Schedulers.newThread()} factory method returns a Scheduler that creates a new thread for each Observer
 * and then destroys the thread when it is no longer needed. This Scheduler does not pool threads and does not attempt
 * to persist and cache threads for reuse, unlike {@code Schedulers.io()}. It can be useful in cases where you want to
 * create, use, and then destroy a thread immediately to minimize memory consumption. However, in complex applications,
 * excessive use of {@code Schedulers.newThread()} can lead to a high volume of threads being created, potentially
 * causing application crashes.
 */
public class NewThreadSchedulerDemo {

    public static void main(String[] args) throws InterruptedException {
        // Create an observable that emits strings.
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.newThread()) // Use the newThread Scheduler.
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
