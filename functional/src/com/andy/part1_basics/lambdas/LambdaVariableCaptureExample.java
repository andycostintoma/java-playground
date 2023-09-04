package com.andy.part1_basics.lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class LambdaVariableCaptureExample {
    public static void main(String[] args) {
        List<Double> cartItems = new ArrayList<>();
        cartItems.add(25.0);
        cartItems.add(15.0);
        cartItems.add(10.0);

        double taxRate = 0.08;

        // Lambda capturing variables from outer scope
        Supplier<Double> totalPriceCalculator = () -> {
            double subtotal = cartItems.stream().mapToDouble(Double::doubleValue).sum();
            return subtotal + (subtotal * taxRate);
        };

        // Calculate and print the total price
        System.out.println("Total price with tax: " + totalPriceCalculator.get());

        // Trying to change a captured variable (will not compile)
        // taxRate = 0.10; // Uncommenting this line will cause a compilation error

        // Re-finalizing a reference using a new variable
        double newTaxRate = taxRate;
        Supplier<Double> totalPriceCalculatorWithNewTax = () -> {
            double subtotal = cartItems.stream().mapToDouble(Double::doubleValue).sum();
            return subtotal + (subtotal * newTaxRate);
        };

        // Calculate and print the total price using the new tax rate
        System.out.println("Total price with new tax rate: " + totalPriceCalculatorWithNewTax.get());
    }
}
