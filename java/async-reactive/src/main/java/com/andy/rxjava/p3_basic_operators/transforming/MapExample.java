package com.andy.rxjava.p3_basic_operators.transforming;

import io.reactivex.rxjava3.core.Observable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Demonstrates the use of the map() operator to transform date strings into LocalDate objects.
 */
public class MapExample {
    public static void main(String[] args) {
        // Define a DateTimeFormatter for date parsing
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M/d/yyyy");

        // Use map() to convert date strings into LocalDate objects
        Observable.just("1/3/2016", "5/9/2016", "10/12/2016")
                .map(s -> LocalDate.parse(s, dtf))
                .subscribe(
                        date -> System.out.println("RECEIVED: " + date),
                        error -> System.err.println("ERROR: " + error),
                        () -> System.out.println("Completed")
                );
    }
}
