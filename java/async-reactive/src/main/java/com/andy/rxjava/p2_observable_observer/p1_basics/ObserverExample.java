package com.andy.rxjava.p2_observable_observer.p1_basics;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * This class demonstrates the usage of the Observer interface to receive and process emissions from an Observable.
 * It covers key concepts such as onNext(), onComplete(), onError(), and the flow of emissions through the Observable chain.
 */
public class ObserverExample {

    /**
     * The main entry point of the program.
     *
     * @param args The command-line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // Create an Observable using Observable.just()
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma");

        // Create an Observer<Integer> to receive and process emissions
        Observer<Integer> myObserver = new Observer<>() {
            @Override
            public void onSubscribe(Disposable d) {
                // Subscription setup (not used in this example)
                // This method can be used to manage subscriptions and resources.
            }

            @Override
            public void onNext(Integer value) {
                // Process and print received values
                System.out.println("RECEIVED: " + value);
            }

            @Override
            public void onError(Throwable e) {
                // Handle and print errors (if they occur in the Observable chain)
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                // Signal completion of emissions (end of data)
                System.out.println("Done!");
            }
        };

        // Chain operators map() and filter() and subscribe the Observer to the Observable
        source.map(String::length)
                .filter(length -> length >= 5)
                .subscribe(myObserver);
    }
}
