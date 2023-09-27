package com.andy.rxjava.p7_concurrency_parallelism.schedulers;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * This class demonstrates managing the life cycle of RxJava schedulers.
 * <p>
 * Each default Scheduler in RxJava is lazily instantiated. You can dispose of a Scheduler created by the
 * computation(), io(), newThread(), single(), or trampoline() factory method at any time by calling its
 * shutdown() method. Alternatively, you can dispose of all created schedulers by calling Schedulers.shutdown().
 * This action stops all their threads and forbids new tasks from coming in. An error is thrown if you attempt to
 * use a disposed scheduler. You can also reinitialize the schedulers by calling their start() method or
 * Schedulers.start(), allowing them to accept tasks again.
 * <p>
 * In most desktop and mobile app environments, you may not need to manually start and stop schedulers. However,
 * in server-side environments like Java EE-based applications (e.g., servlets), where applications may get
 * unloaded and reloaded using different classloaders, it's important to shut down the schedulers properly to
 * prevent leaks. This can be done in the destroy() method of the servlet.
 * <p>
 * It's advisable to manage the life cycle of schedulers only when necessary. Letting schedulers dynamically
 * manage their resources and remain initialized and available is generally a better approach, ensuring tasks
 * can be executed promptly as needed. When shutting down schedulers, make sure that all outstanding tasks are
 * completed or disposed of to avoid leaving processing sequences in an inconsistent state.
 */
public class SchedulerLifecycleManagement {

    public static void main(String[] args) {
        // Example of using a scheduler (computation scheduler) in an observable.
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.computation()) // Using computation scheduler.
                .map(s -> intenseCalculation(s))
                .subscribe(
                        result -> System.out.println("Received: " + result),
                        throwable -> throwable.printStackTrace(),
                        () -> System.out.println("Done!")
                );

        // Shutting down the computation scheduler after use.
        Schedulers.computation().shutdown();
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
