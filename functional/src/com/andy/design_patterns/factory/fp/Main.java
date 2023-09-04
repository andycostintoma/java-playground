package com.andy.design_patterns.factory.fp;

import com.andy.design_patterns.factory.shared.Shape;

import java.awt.Color;

public class Main {
    public static void main(String[] args) {
        Color red = Color.RED;
        Shape circle = ShapeType.CIRCLE.newInstance(red);
        System.out.println("Circle corners: " + circle.corners());
        System.out.println("Circle color: " + circle.color());

        Color blue = Color.BLUE;
        Shape triangle = ShapeType.TRIANGLE.newInstance(blue);
        System.out.println("Triangle corners: " + triangle.corners());
        System.out.println("Triangle color: " + triangle.color());
    }
}