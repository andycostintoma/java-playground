package com.andy.rxjava.p6_subjects;

import io.reactivex.rxjava3.subjects.AsyncSubject;
import io.reactivex.rxjava3.subjects.Subject;

/**
 * Demonstrates the usage of an {@link AsyncSubject}.
 *
 * <p>The {@code AsyncSubject<String>} class has a highly tailored, finite-specific behavior: it pushes
 * only the last value it receives, followed by an onComplete() event. In this example, an observer (Observer 1)
 * subscribes to the AsyncSubject and receives emissions "Gamma" after the onComplete() event is triggered.
 * A second observer (Observer 2) subscribes after onComplete() and also receives "Gamma." The AsyncSubject
 * is suitable for situations where you need to emit only the final result of a computation or event.</p>
 */
public class AsyncSubjectDemo {
    public static void main(String[] args) {
        // Create an AsyncSubject
        Subject<String> subject = AsyncSubject.create();
        
        // Observer 1 subscribes and receives "Gamma" after onComplete()
        subject.subscribe(
            s -> System.out.println("Observer 1: " + s),
            Throwable::printStackTrace,
            () -> System.out.println("Observer 1 done!")
        );
        
        // Emit some values
        subject.onNext("Alpha");
        subject.onNext("Beta");
        subject.onNext("Gamma");
        
        // Trigger onComplete()
        subject.onComplete();
        
        // Observer 2 subscribes after onComplete() and also receives "Gamma"
        subject.subscribe(
            s -> System.out.println("Observer 2: " + s),
            Throwable::printStackTrace,
            () -> System.out.println("Observer 2 done!")
        );
    }
}
