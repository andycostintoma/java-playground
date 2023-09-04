package com.andy.part3_immutability.records;

public class RecordDefaultValuesExample {

    // Origin record with default constructor
    public record Origin(int x, int y) {
        public Origin() {
            this(0, 0);
        }
    }

    // Rectangle record with custom constructors
    public record Rectangle(Origin origin, int width, int height) {
        
        // Custom constructor with Origin
        public Rectangle(int x, int y, int width, int height) {
            this(new Origin(x, y), width, height);
        }

        // Convenience constructor without Origin
        public Rectangle(int width, int height) {
            this(new Origin(), width, height);
        }

        // Factory method for creating Rectangle at specific x-coordinate
        public static Rectangle atX(int x, int width, int height) {
            return new Rectangle(x, 0, width, height);
        }

        // Factory method for creating Rectangle at specific y-coordinate
        public static Rectangle atY(int y, int width, int height) {
            return new Rectangle(0, y, width, height);
        }
    }

    public static void main(String[] args) {
        // Creating Rectangle using different constructors
        Rectangle rectangle1 = new Rectangle(23, 42, 300, 400);
        Rectangle rectangle2 = new Rectangle(300, 400);
        Rectangle rectangle3 = Rectangle.atX(15, 250, 350);
        Rectangle rectangle4 = Rectangle.atY(25, 350, 250);

        // Displaying rectangles
        System.out.println(rectangle1);
        System.out.println(rectangle2);
        System.out.println(rectangle3);
        System.out.println(rectangle4);
    }
}
