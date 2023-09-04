package com.andy.design_patterns.factory.oop;

import com.andy.design_patterns.factory.shared.Shape;

import java.awt.Color;

public class Main {
    public static void main(String[] args) {
        Color red = Color.RED;
        com.andy.design_patterns.factory.shared.Shape circle = ShapeFactory.newShape(ShapeType.CIRCLE, red);
        System.out.println("Circle corners: " + circle.corners());
        System.out.println("Circle color: " + circle.color());

        Color blue = Color.BLUE;
        Shape triangle = ShapeFactory.newShape(ShapeType.TRIANGLE, blue);
        System.out.println("Triangle corners: " + triangle.corners());
        System.out.println("Triangle color: " + triangle.color());
    }
}