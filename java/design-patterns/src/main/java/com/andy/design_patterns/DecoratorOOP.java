package com.andy.part6_design_patterns;



public class DecoratorOOP {

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

    static class MilkDecorator implements Coffee {
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

    static class SugarDecorator implements Coffee {
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


    public static void main(String[] args) {
        Coffee simpleCoffee = new SimpleCoffee();
        System.out.println("Cost: $" + simpleCoffee.cost() + ", Description: " + simpleCoffee.description());

        Coffee coffeeWithMilk = new MilkDecorator(simpleCoffee);
        System.out.println("Cost: $" + coffeeWithMilk.cost() + ", Description: " + coffeeWithMilk.description());

        Coffee coffeeWithMilkAndSugar = new SugarDecorator(coffeeWithMilk);
        System.out.println("Cost: $" + coffeeWithMilkAndSugar.cost() + ", Description: " + coffeeWithMilkAndSugar.description());
    }
}
