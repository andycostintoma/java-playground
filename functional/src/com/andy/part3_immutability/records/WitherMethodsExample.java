package com.andy.part3_immutability.records;

public class WitherMethodsExample {

    public record Point(int x, int y) {

        public Point withX(int newX) {
            return new Point(newX, y());
        }

        public Point withY(int newY) {
            return new Point(x(), newY);
        }
    }

    public static void main(String[] args) {
        Point point = new Point(23, 42);
        System.out.println("Original point: " + point);

        Point newPoint = point.withX(5);
        System.out.println("New point with modified x: " + newPoint);

        Point anotherPoint = point.withY(10);
        System.out.println("New point with modified y: " + anotherPoint);
    }
}
