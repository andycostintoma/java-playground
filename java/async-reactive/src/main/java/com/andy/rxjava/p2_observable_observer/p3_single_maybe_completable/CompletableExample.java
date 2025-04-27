package com.andy.rxjava.p2_observable_observer.p3_single_maybe_completable;

import io.reactivex.rxjava3.core.Completable;

/**
 * This class demonstrates the usage of the Completable class, which represents an action without emissions.
 * It also shows the CompletableObserver interface for handling completion and errors.
 */
public class CompletableExample {

    /**
     * The main method of the program.
     *
     * @param args The command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        Completable.fromRunnable(CompletableExample::runProcess)
                .subscribe(
                        () -> System.out.println("Done!"),
                        error -> System.out.println("Error captured: " + error)
                );
    }

    /**
     * Simulates a process that runs.
     */
    private static void runProcess() {
        // Implement the process logic here
    }
}
