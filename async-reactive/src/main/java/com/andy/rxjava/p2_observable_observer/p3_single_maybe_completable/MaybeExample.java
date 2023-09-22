package com.andy.rxjava.p2_observable_observer.p3_single_maybe_completable;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;

/**
 * This class demonstrates the usage of the Maybe<T> class, which emits 0 or 1 item.
 * It also shows the MaybeObserver interface for handling emissions and completion.
 */
public class MaybeExample {

    /**
     * The main method of the program.
     *
     * @param args The command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // Creating a Maybe with an emission
        Maybe<Integer> source = Maybe.just(100);
        source.subscribe(
                item -> System.out.println("Process 1: " + item),
                error -> System.out.println("Error captured: " + error),
                () -> System.out.println("Process 1 done!")
        );

        // Creating an empty Maybe
        Maybe<Integer> empty = Maybe.empty();
        empty.subscribe(
                item -> System.out.println("Process 2: " + item),
                error -> System.out.println("Error captured: " + error),
                () -> System.out.println("Process 2 done!")
        );

        Observable<String> observable = Observable.just("Alpha", "Beta");
        observable.firstElement() // Returns a Maybe
                .subscribe(s -> System.out.println("RECEIVED " + s),
                        e -> System.out.println("Error captured: " + e),
                        () -> System.out.println("Done!")
                );
    }
}
