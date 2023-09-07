package com.andy.part3_immutability.records;

import java.io.*;

public class RecordSerializationExample {


    record Point(int x, int y) implements Serializable {
    }

    static void serializePoint(Point point, String fileName) {
        try (var out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(point);
            System.out.println("Serialized " + point + " to " + fileName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static Point deserializePoint(String fileName) {
        try (var in = new ObjectInputStream(new FileInputStream(fileName))) {
            Object obj = in.readObject();
            if (obj instanceof Point) {
                return (Point) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        String fileName = "point.txt";
        // Create a point and serialize it
        Point point = new Point(23, 42);
        serializePoint(point, fileName);

        // Deserialize the point and print its values
        Point deserializedPoint = deserializePoint(fileName);
        System.out.println("Deserialized Point: " + deserializedPoint);
    }



}
