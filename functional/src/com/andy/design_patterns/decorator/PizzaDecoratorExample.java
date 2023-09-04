package com.andy.design_patterns.decorator;

import java.util.function.Function;

public class PizzaDecoratorExample {
    public static void main(String[] args) {
        Function<String, String> basicPizza = pizza -> "Basic pizza with " + pizza;

        // Define topping decorator functions using andThen
        Function<String, String> addCheese = basePizza -> basePizza + ", cheese";
        Function<String, String> addPepperoni = basePizza -> basePizza + ", pepperoni";
        Function<String, String> addMushrooms = basePizza -> basePizza + ", mushrooms";

        // Create custom pizzas using decorators with andThen
        Function<String, String> customPizza = basicPizza
                .andThen(addCheese)
                .andThen(addPepperoni)
                .andThen(addMushrooms);

        // Order custom pizzas
        String orderedPizza = customPizza.apply("thin crust");
        System.out.println(orderedPizza);
    }
}
