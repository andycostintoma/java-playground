package com.andy.design_patterns.decorator.fp;


import com.andy.design_patterns.decorator.shared.Coffee;

import java.util.function.UnaryOperator;

public class MilkDecorator implements UnaryOperator<Coffee> {
    @Override
    public Coffee apply(Coffee coffee) {
        return new Coffee() {
            @Override
            public double cost() {
                return coffee.cost() + 1.0;
            }

            @Override
            public String description() {
                return coffee.description() + ", Milk";
            }
        };
    }
}
