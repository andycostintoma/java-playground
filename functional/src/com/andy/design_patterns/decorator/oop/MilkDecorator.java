package com.andy.design_patterns.decorator.oop;

import com.andy.design_patterns.decorator.shared.Coffee;

public class MilkDecorator implements Coffee {
    private final Coffee coffee;

    public MilkDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public double cost() {
        return coffee.cost() + 1.0; // Cost of milk
    }

    @Override
    public String description() {
        return coffee.description() + ", Milk";
    }
}


