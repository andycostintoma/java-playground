package com.andy.rxjava.p6_subjects;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.Subject;
import io.reactivex.rxjava3.subjects.UnicastSubject;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates the usage of an {@link UnicastSubject}.
 *
 * <p>The {@code UnicastSubject<String>} class is a Subject that buffers all the emissions it receives until
 * an Observer subscribes to it. Once an Observer subscribes, it releases all the buffered emissions to that
 * Observer and clears its cache. It can work with only one Observer and throws an error for any subsequent ones.
 * This example showcases the behavior of UnicastSubject, where the first Observer receives buffered emissions
 * and subsequent Observers are not supported. It also demonstrates how to use {@code publish().autoConnect()} to
 * multicast and support multiple Observers for live emissions without receiving missed emissions.</p>
 */
public class UnicastSubjectDemo {
    public static void main(String[] args) {
        // Create a UnicastSubject
        Subject<String> subject = UnicastSubject.create();
        
        // Emit values with an interval
        Observable.interval(300, TimeUnit.MILLISECONDS)
                  .map(l -> ((l + 1) * 300) + " milliseconds")
                  .subscribe(subject);
        
        // Sleep for 2 seconds
        sleep(2000);
        
        // First Observer subscribes and receives buffered emissions
        subject.subscribe(s -> System.out.println("Observer 1: " + s));
        
        // Sleep for 2 seconds
        sleep(2000);
        
        // Second Observer attempts to subscribe but is not supported
        subject.subscribe(s -> System.out.println("Observer 2: " + s));
        
        // Sleep for 1 second
        sleep(1000);
        
        // Multicast to support multiple Observers
        Observable<String> multicast = subject.publish().autoConnect();
        
        // Bring in the first Observer
        multicast.subscribe(s -> System.out.println("Observer 1: " + s));
        
        // Sleep for 2 seconds
        sleep(2000);
        
        // Bring in the second Observer
        multicast.subscribe(s -> System.out.println("Observer 2: " + s));
        
        // Sleep for 1 second
        sleep(1000);
    }
    
    // Helper method to sleep for a specified duration
    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
