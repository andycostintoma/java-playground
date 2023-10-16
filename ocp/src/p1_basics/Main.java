package p1_basics;

public class Main {
    public static void main(String[] args) {

        Point2D p1 = new Point2D(10, 20);  // A point with coordinates (10,20)
        Point2D p2 = new Point2D(5, 15);  // A point with coordinates (5,15)

        Point2D point = new Point2D(-1, -4);   // Creates a point with coordinates (-1,-4)
        point.setX(-2);                             // The x field is set to the value -2
        int yCoord = point.getY();                  // Returns the value -4 of the y field
        System.out.println(point);                  // Prints: (-2,-4)
//        point.distanceFromOrigin();               // Compile-time error: No such method.
//        System.out.println(point.x);              // Compile-time error: x is not accessible.
        System.out.println(point.getX());           // OK.

        double d = Point2D.distance(p1, p2); // Class name to invoke static method
        p1.showInfo();                              // Reference invokes static method (not recommended)

        Point3D p3A = new Point3D(10, 20, 30);
        System.out.println(p3A.toString());               // (10,20,30)       (Point3D)
        System.out.println("x: " + p3A.getX());           // x: 10            (Point2D)
        System.out.println("y: " + p3A.getY());           // y: 20            (Point2D)
        System.out.println("z: " + p3A.getZ());           // z: 30            (Point3D)

        p3A.setX(-10);
        p3A.setY(-20);
        p3A.setZ(-30);
        System.out.println(p3A.toString());               // (-10,-20,-30)    (Point3D)

        Point2D origin = new Point2D(0, 0);
        System.out.println(origin.toString());                  // (0,0)
        System.out.println(origin);                             // (0,0) toString method called implicitly

        Point3D p3B = new Point3D(30, 20, 10);
        System.out.println(p3B.toString());               // (30,20,10)       (Point3D)
        System.out.println(Point3D.distance(p3A, p3B));   // 69.2820323027551 (Point3D)
        Point3D.showInfo(); // A 3D point represented by (x,y,z)-coordinates. (Point3D)

        Line line1 = new Line(new Point2D(5, 6), new Point2D(7, 8));
        System.out.println(line1.toString());               // Line[(5,6),(7,8)]
        line1.setEndPoint1(new Point2D(11, 12));
        line1.setEndPoint2(new Point2D(13, 14));
        System.out.println(line1.toString());               // Line[(11,12),(13,14)]
        System.out.println("Length: " + line1.length());    // Length: 2.8284271247461903
        System.out.print("Don’t terminate this line!");


    }
}
