package com.andy.part6_design_patterns;


import java.awt.*;
import java.util.Objects;


public class FactoryOOP {

    public interface Shape {
        int corners();

        Color color();
    }

    record Circle(Color color) implements Shape {
        public int corners() {
            return 0;
        }
    }

    record Triangle(Color color) implements Shape {
        public int corners() {
            return 3;
        }

    }

    record Square(Color color) implements Shape {
        public int corners() {
            return 4;
        }
    }

    enum ShapeType {
        CIRCLE,
        TRIANGLE,
        SQUARE,
    }

    static class ShapeFactory {

        public static Shape of(ShapeType type, Color color) {
            Objects.requireNonNull(color);

            return switch (type) {
                case CIRCLE -> new Circle(color);
                case TRIANGLE -> new Triangle(color);
                case SQUARE -> new Square(color);
            };
        }
    }

    public static void main(String[] args) {
        Color red = Color.RED;
        Shape circle = ShapeFactory.of(ShapeType.CIRCLE, red);
        System.out.println("Circle corners: " + circle.corners());
        System.out.println("Circle color: " + circle.color());

        Color blue = Color.BLUE;
        Shape triangle = ShapeFactory.of(ShapeType.TRIANGLE, blue);
        System.out.println("Triangle corners: " + triangle.corners());
        System.out.println("Triangle color: " + triangle.color());
    }
}