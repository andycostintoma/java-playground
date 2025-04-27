package com.andy.part4_streams.basics;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Stream;

public class StreamReductionExample {

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

        // Reduction: Find the maximum number of corners using a BinaryOperator
        int maxCorners = shapes.stream()
                .map(Shape::corners)
                .reduce(0, Integer::max);
        System.out.println("Maximum number of corners: " + maxCorners);

        // Reduction: Find the total number of corners using sum()
        int totalCorners = shapes.stream()
                .mapToInt(Shape::corners)
                .sum();
        System.out.println("Total number of corners: " + totalCorners);

        // Find the minimum shape based on the number of corners
        Optional<Shape> minShape = shapes.stream()
                .min(Shape::compareTo);
        minShape.ifPresent(shape -> System.out.println("Shape with the fewest corners: " + shape));

        // Count the number of shapes
        long shapeCount = shapes.stream().count();
        System.out.println("Number of shapes: " + shapeCount);

        // Calculate the average number of corners
        OptionalDouble averageCorners = shapes.stream()
                .mapToInt(Shape::corners)
                .average();
        averageCorners.ifPresent(avg -> System.out.println("Average number of corners: " + avg));

        // Print summary statistics for the number of corners
        IntSummaryStatistics cornerStatistics = shapes.stream()
                .mapToInt(Shape::corners)
                .summaryStatistics();
        System.out.println("Corner Statistics: " + cornerStatistics);

        // Reduction: Using reduce to compute the total length of words
        int totalWordLengthReduce = Stream.of("apple", "orange", "banana")
                .reduce(0,
                        (acc, str) -> acc + str.length(),
                        Integer::sum);
        System.out.println("Total word length (reduce): " + totalWordLengthReduce);

        // Reduction: Using map and reduce to compute the total length of words
        int totalWordLengthMapReduce = Stream.of("apple", "orange", "banana")
                .mapToInt(String::length)
                .reduce(0, Integer::sum);
        System.out.println("Total word length (mapReduce): " + totalWordLengthMapReduce);
    }
}
