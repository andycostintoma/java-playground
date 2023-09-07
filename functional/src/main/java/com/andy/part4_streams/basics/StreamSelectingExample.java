package com.andy.part4_streams.basics;

import java.util.List;

public class StreamSelectingExample {

    record Shape(int corners) implements Comparable<Shape> {

        boolean hasCorners() {
            return corners() > 0;
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
        List<Shape> shapes = List.of(
                Shape.circle(),
                Shape.triangle(),
                Shape.triangle(),
                Shape.square(),
                Shape.circle(),
                Shape.square()
        );

        System.out.println("Shapes with corners:");
        shapes.stream()
                .filter(Shape::hasCorners)
                .forEach(System.out::println);


        System.out.println("\nDropping shapes while corners < 3:");
        shapes.stream()
                .dropWhile(shape -> shape.corners() < 3)
                .forEach(System.out::println);

        System.out.println("\nTaking shapes while corners < 4:");
        shapes.stream()
                .takeWhile(shape -> shape.corners() < 4)
                .forEach(System.out::println);

        System.out.println("\nLimiting to 3 shapes:");
        shapes.stream()
                .limit(3)
                .forEach(System.out::println);

        System.out.println("\nSkipping first 2 shapes:");
        shapes.stream()
                .skip(2)
                .forEach(System.out::println);

        System.out.println("\nDistinct shapes:");
        shapes.stream()
                .distinct()
                .forEach(System.out::println);

        System.out.println("\nSorted shapes by corners:");
        shapes.stream()
                .sorted()
                .forEach(System.out::println);

        System.out.println("\nCustom sorted shapes (reverse order):");
        shapes.stream()
                .sorted((s1, s2) -> Integer.compare(s2.corners(), s1.corners()))
                .forEach(System.out::println);
    }
}
