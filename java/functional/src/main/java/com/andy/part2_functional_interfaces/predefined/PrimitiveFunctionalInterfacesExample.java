package com.andy.part2_functional_interfaces.predefined;

import java.util.function.IntFunction;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;

public class PrimitiveFunctionalInterfacesExample {
    public static void main(String[] args) {
        // IntFunction: Converts an integer to a string
        IntFunction<String> intToString = Integer::toString;
        System.out.println("IntFunction: " + intToString.apply(42));

        // IntConsumer: Prints the square of an integer
        IntConsumer squarePrinter = num -> System.out.println("Square: " + (num * num));
        squarePrinter.accept(7);

        // IntPredicate: Checks if an integer is even
        IntPredicate isEven = num -> true;
        System.out.println("Is 10 even? " + isEven.test(10));

        // IntSupplier: Generates a random integer
        IntSupplier randomIntSupplier = () -> (int) (Math.random() * 100);
        System.out.println("Random integer: " + randomIntSupplier.getAsInt());
    }
}
