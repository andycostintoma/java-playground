package com.andy.rxjava.p2_observable_observer.p1_basics;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

/**
 * This class demonstrates the use of ConnectableObservable to make an Observable hot
 * and ensure emissions are played to all observers at once.
 */
public class ConnectableObservableExample {

    /**
     * The main entry point of the program.
     *
     * @param args The command-line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // Create a ConnectableObservable from a cold Observable using the publish() method
        ConnectableObservable<String> source =
                Observable.just("Alpha", "Beta", "Gamma").publish();

        // Set up Observer 1
        source.subscribe(s -> System.out.println("Observer 1: " + s));

        // Set up Observer 2, which transforms the emissions into the length of strings
        source.map(String::length)
                .subscribe(i -> System.out.println("Observer 2: " + i));

        // Start emitting values to all observers by calling connect()
        source.connect();
    }
}
