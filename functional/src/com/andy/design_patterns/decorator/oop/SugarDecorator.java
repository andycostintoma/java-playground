package com.andy.design_patterns.decorator.oop;

import com.andy.design_patterns.decorator.shared.Coffee;

public class SugarDecorator implements Coffee {
    private final Coffee coffee;

    public SugarDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public double cost() {
        return coffee.cost() + 0.5; // Cost of sugar
    }

    @Override
    public String description() {
        return coffee.description() + ", Sugar";
    }
}