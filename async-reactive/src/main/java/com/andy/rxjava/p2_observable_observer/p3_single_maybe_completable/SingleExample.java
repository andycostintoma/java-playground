package com.andy.rxjava.p2_observable_observer.p3_single_maybe_completable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

/**
 * This class demonstrates the usage of the Single<T> class, which emits only one item,
 * and the SingleObserver interface.
 */
public class SingleExample {

    /**
     * The main method of the program.
     *
     * @param args The command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // Using Single.just() to emit a single item and performing a transformation
        Single.just("Hello!")
                .map(String::length)
                .subscribe(
                        length -> System.out.println("Length: " + length),
                        error -> System.out.println("Error captured: " + error)
                );

        Observable<String> source = Observable.just("Alpha", "Beta");
        source.first("Nil")  //returns a Single
                .subscribe(System.out::println);
    }
}
