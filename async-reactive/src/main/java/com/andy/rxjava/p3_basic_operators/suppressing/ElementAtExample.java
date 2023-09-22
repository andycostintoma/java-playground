package com.andy.rxjava.p3_basic_operators.suppressing;

import io.reactivex.rxjava3.core.Observable;

/**
 * Demonstrates the use of the elementAt() operator to get a specific emission by index.
 */
public class ElementAtExample {
    public static void main(String[] args) {
        // Use elementAt to get the element at index 3
        Observable.just("Alpha", "Beta", "Zeta", "Eta", "Gamma")
                .elementAt(3)
                .subscribe(
                        item -> System.out.println("RECEIVED: " + item),
                        error -> System.err.println("ERROR: " + error),
                        () -> System.out.println("Completed")
                );

        // Other related operators
        // elementAtOrError()
        Observable.just("One", "Two", "Three")
                .elementAtOrError(4)
                .subscribe(
                        item -> System.out.println("RECEIVED: " + item),
                        error -> System.err.println("ERROR: " + error.getMessage())
                );

        // singleElement() - Produces an error if there is more than one element
        Observable.just("OnlyOne")
                .singleElement()
                .subscribe(
                        item -> System.out.println("RECEIVED: " + item),
                        error -> System.err.println("ERROR: " + error.getMessage())
                );

        // firstElement() - Emits the first item
        Observable.just("First", "Second", "Third")
                .firstElement()
                .subscribe(
                        item -> System.out.println("RECEIVED: " + item),
                        error -> System.err.println("ERROR: " + error.getMessage())
                );

        // lastElement() - Emits the last item
        Observable.just("Last1", "Last2", "Last3")
                .lastElement()
                .subscribe(
                        item -> System.out.println("RECEIVED: " + item),
                        error -> System.err.println("ERROR: " + error.getMessage())
                );
    }
}
