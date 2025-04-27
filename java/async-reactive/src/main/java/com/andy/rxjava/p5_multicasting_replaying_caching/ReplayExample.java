package com.andy.rxjava.p5_multicasting_replaying_caching;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class ReplayExample {
    public static void main(String[] args) {
        // Example with replay() and autoConnect()
        Observable<Long> ints =
                Observable.interval(1, TimeUnit.SECONDS).replay().autoConnect();

        // Observer 1
        Disposable disposable1 = ints.subscribe(l -> System.out.println("Observer 1: " + l));
        sleep(3000);

        // Observer 2
        Disposable disposable2 = ints.subscribe(l -> System.out.println("Observer 2: " + l));
        sleep(3000);

        disposable1.dispose();
        disposable2.dispose();

        // Example with replay(2)
        Observable<Long> intsReplay2 =
                Observable.interval(1, TimeUnit.SECONDS).replay(2).autoConnect();

        // Observer 3
        Disposable disposable3 = intsReplay2.subscribe(l -> System.out.println("Observer 3: " + l));
        sleep(3000);

        // Observer 4
        Disposable disposable4 = intsReplay2.subscribe(l -> System.out.println("Observer 4: " + l));

        disposable3.dispose();
        disposable4.dispose();

        // Example with replay(1).autoConnect()
        Observable<String> source =
                Observable.just("Alpha", "Beta", "Gamma").replay(1).autoConnect();

        // Observer 5
        Disposable disposable5 = source.subscribe(l -> System.out.println("Observer 5: " + l));

        // Observer 6
        Disposable disposable6 = source.subscribe(l -> System.out.println("Observer 6: " + l));

        disposable5.dispose();
        disposable6.dispose();

        // Example with refCount() instead of autoConnect()
        Observable<String> sourceRefCount =
                Observable.just("Alpha", "Beta", "Gamma").replay(1).refCount();

        // Observer 7
        Disposable disposable7 = sourceRefCount.subscribe(l -> System.out.println("Observer 7: " + l));

        // Observer 8
        Disposable disposable8 = sourceRefCount.subscribe(l -> System.out.println("Observer 8: " + l));

        disposable7.dispose();
        disposable8.dispose();

        // Example with replay() and time-based window
        Observable<Long> seconds =
                Observable.interval(300, TimeUnit.MILLISECONDS)
                        .map(l -> (l + 1) * 300)
                        .replay(1, TimeUnit.SECONDS)
                        .autoConnect();

        // Observer 9
        Disposable disposable9 = seconds.subscribe(l -> System.out.println("Observer 9: " + l));
        sleep(2000);

        // Observer 10
        Disposable disposable10 = seconds.subscribe(l -> System.out.println("Observer 10: " + l));

        disposable9.dispose();
        disposable10.dispose();

        // Example with replay() and time-based window and buffer size
        Observable<Long> secondsBuffered =
                Observable.interval(300, TimeUnit.MILLISECONDS)
                        .map(l -> (l + 1) * 300)
                        .replay(1, 1, TimeUnit.SECONDS)
                        .autoConnect();

        // Observer 11
        Disposable disposable11 = secondsBuffered.subscribe(l -> System.out.println("Observer 11: " + l));

        // Observer 12
        Disposable disposable12 = secondsBuffered.subscribe(l -> System.out.println("Observer 12: " + l));

        disposable11.dispose();
        disposable12.dispose();
    }

    /**
     * Sleeps the current thread for the specified duration.
     *
     * @param milliseconds The duration to sleep in milliseconds.
     */
    private static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
