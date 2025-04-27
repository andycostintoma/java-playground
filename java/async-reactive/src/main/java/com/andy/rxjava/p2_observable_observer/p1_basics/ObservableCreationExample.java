package com.andy.rxjava.p2_observable_observer.p1_basics;

import io.reactivex.rxjava3.core.Observable;
import java.util.List;

/**
 * This class demonstrates the usage of {@link Observable#just()} and {@link Observable#fromIterable()} to create Observable objects.
 */
public class ObservableCreationExample {

    /**
     * The main entry point of the program.
     *
     * @param args The command-line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // Create an Observable using Observable.just()
        Observable<String> sourceJust =
                Observable.just("Alpha", "Beta", "Gamma");

        // Chaining operators map() and filter() to transform and filter emitted data
        sourceJust.map(String::length)
                .filter(length -> length >= 5)
                .subscribe(s -> System.out.println("RECEIVED (Observable.just()): " + s));

        // Create an Observable using Observable.fromIterable() from a List
        List<String> items = List.of("Alpha", "Beta", "Gamma");
        Observable<String> sourceFromIterable = Observable.fromIterable(items);

        // Chaining operators map() and filter() to transform and filter emitted data
        sourceFromIterable.map(String::length)
                .filter(length -> length >= 5)
                .subscribe(s -> System.out.println("RECEIVED (Observable.fromIterable()): " + s));
    }
}
