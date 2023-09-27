package com.andy.rxjava.p6_subjects;

import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

/**
 * Demonstrates the usage of the {@link PublishSubject}, which is a type of Subject
 * in RxJava. Subjects can manually emit items to their subscribers by calling {@code onNext()},
 * {@code onComplete()}, and {@code onError()}.
 *
 * <p>In this example, a {@code PublishSubject<String>} is created. An observer subscribes to it,
 * mapping the length of strings and printing the results. Strings are then manually emitted using
 * {@code onNext()}, and the subject signals completion with {@code onComplete()}.</p>
 */
public class PublishSubjectExample {
    public static void main(String[] args) {
        // Create a PublishSubject of type String
        Subject<String> subject = PublishSubject.create();
        
        // Subscribe an observer to the subject, mapping the length of strings and printing the results
        subject.map(String::length)
               .subscribe(System.out::println);

        // Manually emit strings using onNext()
        subject.onNext("Alpha");
        subject.onNext("Beta");
        subject.onNext("Gamma");
        
        // Signal completion to the subject
        subject.onComplete();
    }
}
