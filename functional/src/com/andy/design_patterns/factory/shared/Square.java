package com.andy.design_patterns.factory.shared;

import java.awt.Color;

public record Square(Color color) implements Shape {

    public int corners() {
        return 4;
    }
}
