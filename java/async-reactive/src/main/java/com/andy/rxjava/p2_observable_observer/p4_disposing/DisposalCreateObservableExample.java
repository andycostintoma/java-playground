package com.andy.rxjava.p2_observable_observer.p4_disposing;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class DisposalCreateObservableExample {
    public static void main(String[] args) {
        Observable<Integer> source = Observable.create(observableEmitter -> {
            try {
                for (int i = 0; i < 1000; i++) {
                    // Check if disposal was requested before emitting
                    if (!observableEmitter.isDisposed()) {
                        observableEmitter.onNext(i);
                    } else {
                        return; // Terminate emission if disposed
                    }
                }
                // Emit onComplete if no early termination
                if (!observableEmitter.isDisposed()) {
                    observableEmitter.onComplete();
                }
            } catch (Throwable e) {
                // Emit onError in case of an exception
                observableEmitter.onError(e);
            }
        });

        // Subscribe to the observable and dispose it after a while
        Disposable disposable = source.subscribe(
            value -> System.out.println("Received: " + value),
            Throwable::printStackTrace,
            () -> System.out.println("Done!")
        );

        // Sleep for a while
        sleep(500);

        // Dispose of the subscription
        disposable.dispose();

        // Sleep again to demonstrate no more emissions after disposal
        sleep(500);
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
