package p1_basics;

public class Point2DObjects {
    public static void main(String[] args) {

        Point2D p1 = new Point2D(10, 20);  // A point with coordinates (10,20)
        Point2D p2 = new Point2D(5, 15);  // A point with coordinates (5,15)

        Point2D point = new Point2D(-1, -4);   // Creates a point with coordinates (-1,-4)
        point.setX(-2);                        // (1) The x field is set to the value -2
        int yCoord = point.getY();             // (2) Returns the value -4 of the y field
        System.out.println(point);             // (3) Prints: (-2,-4)
//        point.distanceFromOrigin();          // (4) Compile-time error: No such method.


    }
}
