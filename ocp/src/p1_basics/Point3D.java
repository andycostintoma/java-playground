package p1_basics;

public class Point3D extends Point2D {

    // Static variable:
    private static final String info = "A 3D point represented by (x,y,z)-coordinates.";

    // Instance variable:
    private int z;

    // Constructor:
    public Point3D(int xCoord, int yCoord, int zCoord) {
        super(xCoord, yCoord);
        z = zCoord;
    }

    // Instance methods:
    public int getZ() {
        return z;
    }

    public void setZ(int zCoord) {
        z = zCoord;
    }

    @Override
    public String toString() {
        return "(" + getX() + "," + getY() + "," + z + ")"; // Format: (x,y,z)
    }

    // Static methods:
    public static double distance(Point3D p1, Point3D p2) {
        int xDiff = p1.getX() - p2.getX();
        int yDiff = p1.getY() - p2.getY();
        int zDiff = p1.getZ() - p2.getZ();
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff + zDiff * zDiff);
    }

    public static void showInfo() {
        System.out.println(info);
    }
}
