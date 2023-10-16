package p1_basics;

public class Point2D {             // Class name
    // Class Member Declarations

    // Fields:
    private int x;     // The x-coordinate
    private int y;     // The y-coordinate

    // Constructor:
    public Point2D(int xCoord, int yCoord) {
        x = xCoord;
        y = yCoord;
    }

    // Methods:
    public int getX() {
        return x;
    }

    public void setX(int xCoord) {
        x = xCoord;
    }

    public int getY() {
        return y;
    }

    public void setY(int yCoord) {
        y = yCoord;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    } // Format: (x,y)
}