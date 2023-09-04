package com.andy.design_patterns.factory.fp;

import java.awt.Color;
import java.util.Objects;
import java.util.function.Function;

import com.andy.design_patterns.factory.shared.Circle;
import com.andy.design_patterns.factory.shared.Pentagon;
import com.andy.design_patterns.factory.shared.Square;
import com.andy.design_patterns.factory.shared.Triangle;
import com.andy.design_patterns.factory.shared.Shape;

public enum ShapeType {
    CIRCLE(Circle::new),
    TRIANGLE(Triangle::new),
    SQUARE(Square::new),
    PENTAGON(Pentagon::new);

    public final Function<Color, Shape> factory;

    ShapeType(Function<Color, Shape> factory) {
        this.factory = factory;
    }

    public Shape newInstance(Color color) {
        Objects.requireNonNull(color);
        return this.factory.apply(color);
    }
}