package com.andy.part4_streams.basics;

import java.util.List;

public class StreamMappingExample {

    // Define the Shape record
    record Shape(int corners) implements Comparable<Shape> {

        boolean hasCorners() {
            return corners() > 0;
        }

        List<Shape> twice() {
            return List.of(this, this);
        }

        @Override
        public int compareTo(Shape o) {
            return Integer.compare(corners(), o.corners());
        }

        static Shape circle() {
            return new Shape(0);
        }

        static Shape triangle() {
            return new Shape(3);
        }

        static Shape square() {
            return new Shape(4);
        }
    }

    public static void main(String[] args) {
        // Create a list of Shape instances
        List<Shape> shapes = List.of(
                Shape.circle(),
                Shape.triangle(),
                Shape.triangle(),
                Shape.square(),
                Shape.circle(),
                Shape.square()
        );

        // Print the original shapes
        System.out.println("Original shapes:");
        shapes.forEach(shape -> System.out.println("Shape[corners=" + shape.corners() + "]"));

        // Map the shapes to the number of corners and print
        System.out.println("\nMapping to number of corners:");
        shapes.stream()
                .map(Shape::corners)
                .forEach(corners -> System.out.println("Corners: " + corners));

        // FlatMap to twice the shapes and print
        System.out.println("\nFlatMapping to twice the shapes:");
        shapes.stream()
                .map(Shape::twice)
                .flatMap(List::stream)
                .forEach(System.out::println);

        // Using mapMulti to print twice the shapes
        System.out.println("\nUsing mapMulti to print twice the shapes:");
        shapes.stream()
                .mapMulti((shape, downstream) -> shape.twice()
                        .forEach(downstream))
                .forEach(System.out::println);

        // Peek into the stream, print and filter
        System.out.println("\nPeeking into the stream:");
        List<Shape> result = shapes.stream()
                .map(Shape::twice)
                .flatMap(List::stream)
                .peek(shape -> System.out.println("current: Shape[corners=" + shape.corners() + "]"))
                .filter(shape -> shape.corners() < 4)
                .toList();
    }
}
