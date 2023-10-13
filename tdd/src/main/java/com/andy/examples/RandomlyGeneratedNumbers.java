package com.andy.examples;

import java.util.random.RandomGenerator;

public class RandomlyGeneratedNumbers implements RandomNumbers {
    @Override
    public int nextInt(int upperBoundExclusive) {
        var rnd = RandomGenerator.getDefault();
        return rnd.nextInt(upperBoundExclusive);
    }
}
