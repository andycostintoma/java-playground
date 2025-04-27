package com.andy.rxjava.p6_subjects;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates the usage of a {@link PublishSubject} to eagerly subscribe to an unknown number
 * of multiple source observables and consolidate their emissions into a single Observable.
 *
 * <p>In this example, two source observables {@code source1} and {@code source2} are created.
 * A {@code PublishSubject<String>} is used to consolidate their emissions. The subject is subscribed
 * to both source observables, and its emissions are printed. The output shows emissions from both
 * sources interleaved together.</p>
 */
public class PublishSubjectUsage {
    public static void main(String[] args) {
        // Create two source observables
        Observable<String> source1 =
            Observable.interval(1, TimeUnit.SECONDS)
                      .map(l -> (l + 1) + " seconds");
        Observable<String> source2 =
            Observable.interval(300, TimeUnit.MILLISECONDS)
                      .map(l -> ((l + 1) * 300) + " milliseconds");
        
        // Create a PublishSubject to consolidate emissions
        Subject<String> subject = PublishSubject.create();
        
        // Subscribe to the subject and print emissions
        subject.subscribe(System.out::println);
        
        // Subscribe the subject to the source observables
        source1.subscribe(subject);
        source2.subscribe(subject);
        
        // Sleep for a while to allow emissions to complete
        sleep(3000);
    }

    // Sleep function to simulate time passing
    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
