package com.andy.rxjava.p6_subjects;

import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.Subject;

/**
 * Demonstrates the usage of a {@link BehaviorSubject}.
 *
 * <p>A {@code BehaviorSubject<String>} is created, which behaves similarly to a {@code PublishSubject},
 * but it also replays the last emitted item to each new observer downstream. In this example,
 * an observer (Observer 1) subscribes to the BehaviorSubject and receives emissions "Alpha," "Beta,"
 * and "Gamma." These emissions are then replayed to a second observer (Observer 2) when it subscribes
 * later, resulting in both observers receiving the last emitted item "Gamma."</p>
 */
public class BehaviorSubjectDemo {
    public static void main(String[] args) {
        // Create a BehaviorSubject
        Subject<String> subject = BehaviorSubject.create();
        
        // Observer 1 subscribes and receives emissions
        subject.subscribe(s -> System.out.println("Observer 1: " + s));
        
        // Emit some values
        subject.onNext("Alpha");
        subject.onNext("Beta");
        subject.onNext("Gamma");
        
        // Observer 2 subscribes and receives the last emitted item
        subject.subscribe(s -> System.out.println("Observer 2: " + s));
    }
}
