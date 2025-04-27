package com.andy.part6_design_patterns;


import java.awt.Color;
import java.util.Objects;
import java.util.function.Function;

public class FactoryFP {

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

    enum ShapeFactory {

        CIRCLE(Circle::new),
        TRIANGLE(Triangle::new),
        SQUARE(Square::new);

        private final Function<Color, Shape> factory;

        ShapeFactory(Function<Color, Shape> factory) {
            this.factory = factory;
        }

        public Shape of(Color color) {
            Objects.requireNonNull(color);
            return this.factory.apply(color);
        }
    }


    public static void main(String[] args) {
        Color red = Color.RED;
        Shape circle = ShapeFactory.CIRCLE.of(red);
        System.out.println("Circle corners: " + circle.corners());
        System.out.println("Circle color: " + circle.color());

        Color blue = Color.BLUE;
        Shape triangle = ShapeFactory.TRIANGLE.of(blue);
        System.out.println("Triangle corners: " + triangle.corners());
        System.out.println("Triangle color: " + triangle.color());
    }
}