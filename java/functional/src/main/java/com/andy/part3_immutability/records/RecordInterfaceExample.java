package com.andy.part3_immutability.records;

public class RecordInterfaceExample {

     interface Origin {
        int x();
        int y();

        default String origin() {
            return String.format("(%d,%d)", x(), y());
        }
    }

    interface Area {
        float area();
    }

    // Record implementing the Origin interface
    record Point(int x, int y) implements Origin {
    }

    // Record implementing both Origin and Area interfaces
    record Rectangle(int x, int y, int width, int height) implements Origin, Area {

        @Override
        public float area() {
            return (float) (width() * height());
        }
    }

    // Record implementing both Origin and Area interfaces
    record Circle(int x, int y, int radius) implements Origin, Area {

        public float area() {
            return (float) Math.PI * radius() * radius();
        }
    }

    public static void main(String[] args) {
        Point point = new Point(5, 10);
        System.out.println("Point origin: " + point.origin());

        Rectangle rectangle = new Rectangle(2, 3, 10, 5);
        System.out.println("Rectangle origin: " + rectangle.origin());
        System.out.println("Rectangle area: " + rectangle.area());

        Circle circle = new Circle(7, 7, 5);
        System.out.println("Circle origin: " + circle.origin());
        System.out.println("Circle area: " + circle.area());
    }
}
