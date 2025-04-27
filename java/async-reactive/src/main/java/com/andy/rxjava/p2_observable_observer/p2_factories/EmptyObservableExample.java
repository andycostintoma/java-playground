package com.andy.rxjava.p2_observable_observer.p2_factories;

import io.reactivex.rxjava3.core.Observable;

public class EmptyObservableExample {

    public static void main(String[] args) {
        // Create an empty Observable
        Observable<String> empty = Observable.empty();

        // Subscribe to the empty Observable
        empty.subscribe(
            System.out::println,                // onNext: Print emitted values (none in this case)
            Throwable::printStackTrace,        // onError: Handle errors (not expected in this case)
            () -> System.out.println("Done!")  // onComplete: Handle completion
        );
    }
}
