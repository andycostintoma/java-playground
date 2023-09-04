package com.andy.part2_functional_interfaces.predefined;

import java.util.function.Supplier;

public class SupplierExample {
    public static void main(String[] args) {
        // Define a Supplier to generate a random number
        Supplier<Integer> randomNumberSupplier = () -> (int) (Math.random() * 100);

        // Get a random number using the Supplier
        int randomNumber = randomNumberSupplier.get();
        System.out.println("Random Number: " + randomNumber);
    }
}
