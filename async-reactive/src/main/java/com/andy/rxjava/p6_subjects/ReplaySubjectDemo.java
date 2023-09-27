package com.andy.rxjava.p6_subjects;

import io.reactivex.rxjava3.subjects.ReplaySubject;
import io.reactivex.rxjava3.subjects.Subject;

/**
 * Demonstrates the usage of a {@link ReplaySubject}.
 *
 * <p>The {@code ReplaySubject<String>} class behaves similarly to a {@code PublishSubject}
 * followed by a {@code cache()} operator. It immediately captures emissions regardless of
 * the presence of a downstream Observer and optimizes the caching to occur inside the Subject itself.
 * In this example, an observer (Observer 1) subscribes to the ReplaySubject and receives emissions
 * "Alpha," "Beta," and "Gamma." These emissions are then replayed to a second observer (Observer 2)
 * when it subscribes later, ensuring that both observers receive all emitted items.</p>
 */
public class ReplaySubjectDemo {
    public static void main(String[] args) {
        // Create a ReplaySubject
        Subject<String> subject = ReplaySubject.create();
        
        // Observer 1 subscribes and receives emissions
        subject.subscribe(s -> System.out.println("Observer 1: " + s));
        
        // Emit some values
        subject.onNext("Alpha");
        subject.onNext("Beta");
        subject.onNext("Gamma");
        subject.onComplete();
        
        // Observer 2 subscribes and receives all previously emitted items
        subject.subscribe(s -> System.out.println("Observer 2: " + s));
    }
}
