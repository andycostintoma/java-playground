package com.andy.design_patterns.factory.shared;

import java.awt.Color;

public record Circle(Color color) implements Shape {

    public int corners() {
        return 0;
    }
}
