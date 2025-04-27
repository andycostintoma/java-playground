package com.andy.part1_basics.lambdas;



public class FunctionalGenericInterfaceExample {

    @FunctionalInterface
    interface Converter<T, R> {
        R convert(T input);
    }

    public static void main(String[] args) {
        // Using the Converter functional generic interface to create specific converters

        // Convert Integer to String
        Converter<Integer, String> intToStringConverter = String::valueOf;
        String strValue = intToStringConverter.convert(42);
        System.out.println("Integer to String: " + strValue);

        // Convert Double to Integer
        Converter<Double, Integer> doubleToIntConverter = Double::intValue;
        int intValue = doubleToIntConverter.convert(3.14);
        System.out.println("Double to Integer: " + intValue);
    }
}
