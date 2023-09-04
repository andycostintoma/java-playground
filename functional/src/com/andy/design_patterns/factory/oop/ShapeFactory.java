package com.andy.design_patterns.factory.oop;

import com.andy.design_patterns.factory.shared.*;

import java.awt.Color;
import java.util.Objects;

public class ShapeFactory {

  public static Shape newShape(ShapeType type,
                               Color color) {
    Objects.requireNonNull(color);

    return switch (type) {
      case CIRCLE -> new Circle(color);
      case TRIANGLE -> new Triangle(color);
      case SQUARE -> new Square(color);
      case PENTAGON -> new Pentagon(color);
    };
  }
}