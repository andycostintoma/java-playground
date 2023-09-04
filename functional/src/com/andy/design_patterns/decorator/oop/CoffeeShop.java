package com.andy.design_patterns.decorator.oop;

import com.andy.design_patterns.decorator.shared.Coffee;
import com.andy.design_patterns.decorator.shared.SimpleCoffee;

public class CoffeeShop {
    public static void main(String[] args) {
        Coffee simpleCoffee = new SimpleCoffee();
        System.out.println("Cost: $" + simpleCoffee.cost() + ", Description: " + simpleCoffee.description());

        Coffee coffeeWithMilk = new MilkDecorator(simpleCoffee);
        System.out.println("Cost: $" + coffeeWithMilk.cost() + ", Description: " + coffeeWithMilk.description());

        Coffee coffeeWithMilkAndSugar = new SugarDecorator(coffeeWithMilk);
        System.out.println("Cost: $" + coffeeWithMilkAndSugar.cost() + ", Description: " + coffeeWithMilkAndSugar.description());
    }
}
