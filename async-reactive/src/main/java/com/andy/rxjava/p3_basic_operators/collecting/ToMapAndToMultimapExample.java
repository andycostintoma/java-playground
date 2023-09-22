import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Demonstrates the use of toMap() and toMultimap() operators in RxJava.
 * - toMap() collects values into a Map based on a key derived by a function.
 * - toMultimap() collects values into a Map where keys may have multiple associated values.
 */
public class ToMapAndToMultimapExample {

    public static void main(String[] args) {
        // Example using toMap()
        Observable.just("Alpha", "Beta", "Gamma")
                .toMap(s -> s.charAt(0))
                .subscribe(s -> System.out.println("toMap() - Received: " + s));

        // Example using toMap() with value mapping
        Observable.just("Alpha", "Beta", "Gamma")
                .toMap(s -> s.charAt(0), String::length)
                .subscribe(s -> System.out.println("toMap() with value mapping - Received: " + s));

        // Example using toMap() with a custom Map implementation (ConcurrentHashMap)
        Observable.just("Alpha", "Beta", "Gamma")
                .toMap(s -> s.charAt(0), String::length, ConcurrentHashMap::new)
                .subscribe(s -> System.out.println("toMap() with custom Map implementation - Received: " + s));

        // Example using toMap() with key collision (last value replaces previous ones)
        Observable.just("Alpha", "Beta", "Gamma")
                .toMap(String::length)
                .subscribe(s -> System.out.println("toMap() with key collision - Received: " + s));

        // Example using toMultimap()
        Observable.just("Alpha", "Beta", "Gamma")
                .toMultimap(String::length)
                .subscribe(s -> System.out.println("toMultimap() - Received: " + s));
    }
}
