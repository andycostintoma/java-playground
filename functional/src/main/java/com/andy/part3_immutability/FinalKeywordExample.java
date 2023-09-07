package com.andy.part3_immutability;

public class FinalKeywordExample {

    public static void main(String[] args) {

        // Final class that cannot be subclassed
        final class Shape {
            // Final field that must be assigned in constructors or on declaration
            private final String type;

            public Shape(String type) {
                this.type = type;
            }

            // Final method that cannot be overridden
            public final String getType() {
                return type;
            }
        }
        // Final variable reference
        final int num = 42;

        System.out.println("Final variable: " + num);

        // Creating an instance of the final class
        Shape shape = new Shape("Circle");
        System.out.println("Shape type: " + shape.getType());
    }
}
