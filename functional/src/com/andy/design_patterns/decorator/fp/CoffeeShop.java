package com.andy.design_patterns.decorator.fp;

import com.andy.design_patterns.decorator.shared.Coffee;
import com.andy.design_patterns.decorator.shared.SimpleCoffee;

import java.util.function.UnaryOperator;

public class CoffeeShop {
    public static void main(String[] args) {

        Coffee simpleCoffee = new SimpleCoffee();

        UnaryOperator<Coffee> milkDecorator = new MilkDecorator();
        UnaryOperator<Coffee> sugarDecorator = new SugarDecorator();

        Coffee customizedCoffee = (milkDecorator.andThen(sugarDecorator)).apply(simpleCoffee);

        System.out.println("Cost: " + customizedCoffee.cost() + ", Description: " + customizedCoffee.description());
    }
}
