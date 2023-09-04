package com.andy.design_patterns.decorator.fp;

import com.andy.design_patterns.decorator.shared.Coffee;

import java.util.function.UnaryOperator;

public class SugarDecorator implements UnaryOperator<Coffee> {
    @Override
    public Coffee apply(Coffee coffee) {
        return new Coffee() {
            @Override
            public double cost() {
                return coffee.cost() + 0.5;
            }

            @Override
            public String description() {
                return coffee.description() + ", Sugar";
            }
        };
    }
}
