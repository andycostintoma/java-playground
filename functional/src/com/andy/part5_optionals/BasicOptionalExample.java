package com.andy.part5_optionals;

import java.util.Optional;
import java.util.Random;

public class BasicOptionalExample {
    public static void main(String[] args) {
        Optional<Integer> nonEmptyOptional = Optional.of(42);
        Optional<Integer> emptyOptional = Optional.empty();

        System.out.println(nonEmptyOptional);
        System.out.println(emptyOptional);

        Optional<Integer> optional1 = Optional.ofNullable(generateRandomValueOrNull());
        Optional<Integer> optional2 = Optional.ofNullable(generateRandomValueOrNull());
        Optional<Integer> optional3 = Optional.ofNullable(generateRandomValueOrNull());

        System.out.println(optional1);
        System.out.println(optional2);
        System.out.println(optional3);

        int value1 = 0;
        if (optional1.isPresent()) {
            value1 = optional1.get();
        }

        int value2 = optional2.orElse(0);

        Random random = new Random();
        int value3 = optional3.orElseGet(random::nextInt);

        System.out.println(value1);
        System.out.println(value2);
        System.out.println(value3);

    }

    private static Integer generateRandomValueOrNull() {
        Random random = new Random();
        if (random.nextBoolean()) {
            return random.nextInt(100) + 1;
        } else {
            return null;
        }
    }

}
