package com.andy.design_patterns.decorator.shared;

public class SimpleCoffee implements Coffee {
    @Override
    public double cost() {
        return 2.0; // Cost of a simple coffee
    }

    @Override
    public String description() {
        return "Simple Coffee";
    }
}
