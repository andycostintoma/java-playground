package com.andy.part4_streams.basics;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.stream.Stream;

public class DateTimeExample {

    public static void main(String[] args) {
        // Dealing with Date and Time

        // Querying Temporal Types
        boolean isItTeaTime = LocalDateTime.now()
                .query(temporal -> {
                    var time = LocalTime.from(temporal);
                    return time.getHour() >= 16;
                });

        System.out.println("Is it tea time? " + isItTeaTime);

        LocalTime time = LocalDateTime.now().query(LocalTime::from);
        System.out.println("Now: " + time);

        // LocalDate-Range Streams
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);

        Stream<LocalDate> dateStream = startDate.datesUntil(endDate);
        dateStream.forEach(System.out::println);

        Stream<LocalDate> dateStreamWithStep = startDate.datesUntil(endDate, Period.ofMonths(1));
        dateStreamWithStep.forEach(System.out::println);
    }
}
