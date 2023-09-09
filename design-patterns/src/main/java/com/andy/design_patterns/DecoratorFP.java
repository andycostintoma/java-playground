package com.andy.part6_design_patterns;

import java.util.function.UnaryOperator;

public class DecoratorFP {

    interface Coffee {
        double cost();
        String description();
    }

    static class SimpleCoffee implements Coffee {
        @Override
        public double cost() {
            return 2.0; // Cost of a simple coffee
        }

        @Override
        public String description() {
            return "Simple Coffee";
        }
    }

    static class MilkDecorator implements UnaryOperator<Coffee> {
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

    static class SugarDecorator implements UnaryOperator<Coffee> {
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

    public static void main(String[] args) {

        Coffee simpleCoffee = new SimpleCoffee();

        UnaryOperator<Coffee> milkDecorator = new MilkDecorator();
        UnaryOperator<Coffee> sugarDecorator = new SugarDecorator();

        Coffee customizedCoffee = (milkDecorator.andThen(sugarDecorator)).apply(simpleCoffee);

        System.out.println("Cost: " + customizedCoffee.cost() + ", Description: " + customizedCoffee.description());
    }
}
