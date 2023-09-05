package com.andy.part5_optionals_exceptions;

import java.util.Optional;
import java.util.Random;

public class OptionalOperatorsExample {
    public static void main(String[] args) {

        Optional<Integer> optional = Optional.ofNullable(generateRandomValueOrNull());
        System.out.println(optional);

        // map Operator
        Optional<String> mappedValue = optional.map(value -> "Value: " + value);
        mappedValue.ifPresent(System.out::println);

        // filter Operator
        Optional<Integer> filteredValue = optional.filter(value -> value > 50);
        filteredValue.ifPresent(value -> System.out.println("Value greater than 50: " + value));

        // flatMap Operator
        Optional<String> flatMappedValue = optional.flatMap(value -> Optional.of("Mapped: " + value));
        flatMappedValue.ifPresent(System.out::println);

        // ifPresent Operator
        optional.ifPresent(value -> System.out.println("Value is present: " + value));

        // ifPresentOrElse Operator
        optional.ifPresentOrElse(
                value -> System.out.println("Value is present: " + value),
                () -> System.out.println("Value is not present")
        );


        // or Operator
        Optional<Integer> result = optional.or(() -> Optional.of(generateRandom()));
        System.out.println("Result: " + result);

        // equals and hashCode Operators
        Optional<Integer> optionalValue1 = Optional.ofNullable(generateRandomValueOrNull());
        Optional<Integer> optionalValue2 = Optional.ofNullable(generateRandomValueOrNull());
        boolean areEqual = optionalValue1.equals(optionalValue2);
        System.out.println("Are Equal: " + areEqual);

        int hashCode1 = optionalValue1.hashCode();
        int hashCode2 = optionalValue2.hashCode();
        System.out.println("Hash Code 1: " + hashCode1);
        System.out.println("Hash Code 2: " + hashCode2);
    }

    private static Integer generateRandom() {
        Random random = new Random();
        return random.nextInt(100) + 1;

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
