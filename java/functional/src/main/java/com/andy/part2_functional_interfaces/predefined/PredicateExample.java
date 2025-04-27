package com.andy.part2_functional_interfaces.predefined;

import java.util.function.Predicate;

public class PredicateExample {

    public static void main(String[] args) {

        Predicate<Integer> evenPredicate = number -> number % 2 == 0;

        Integer number = 4;
        boolean isEven = evenPredicate.test(number);
        System.out.println(number + " is even? " + isEven);

        number = 5;
        isEven = evenPredicate.test(number);
        System.out.println(number + " is even? " + isEven);

    }

}
