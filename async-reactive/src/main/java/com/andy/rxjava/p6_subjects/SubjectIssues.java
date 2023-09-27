package com.andy.rxjava.p6_subjects;

import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

/**
 * Demonstrates the potential issues when using a {@link PublishSubject} with emissions
 * occurring before observers are set up.
 *
 * <p>In this example, a {@code PublishSubject<String>} is created. It immediately emits
 * "Alpha," "Beta," and "Gamma" using {@code onNext()}. However, since there are no observers
 * set up at this point, these emissions are missed. Subsequently, it attempts to map the
 * emissions to their lengths, but there are no emissions, resulting in no output.</p>
 */
public class SubjectIssues {
    public static void main(String[] args) {
        // Create a PublishSubject
        Subject<String> subject = PublishSubject.create();
        
        // Emit some values immediately
        subject.onNext("Alpha");
        subject.onNext("Beta");
        subject.onNext("Gamma");
        
        // Complete the subject
        subject.onComplete();
        
        // Attempt to map emissions to their lengths and print (no output)
        subject.map(String::length)
               .subscribe(System.out::println);
    }
}
