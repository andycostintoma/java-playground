package com.andy.rxjava.p2_observable_observer.p1_basics;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;

/**
 * This class demonstrates the usage of shorthand Observer implementations using lambda expressions and the subscribe() method overloads.
 * It covers the onNext, onError, and onComplete events and emphasizes the importance of handling errors.
 */
public class ObserverShorthandExample {

    /**
     * The main entry point of the program.
     *
     * @param args The command-line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // Create an Observable using Observable.just()
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma");

        // Shorthand lambda expressions for onNext, onError, and onComplete events
        Consumer<Integer> onNext = i -> System.out.println("RECEIVED: " + i);
        Consumer<Throwable> onError = Throwable::printStackTrace;
        Action onComplete = () -> System.out.println("Done!");

        // Subscribe using shorthand lambda expressions
        source.map(String::length)
              .filter(i -> i >= 5)
              .subscribe(
                  onNext, // Handle onNext event
                  onError, // Handle onError event (print stack trace)
                  onComplete // Handle onComplete event (print "Done!")
              );
    }
}
