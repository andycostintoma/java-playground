package com.andy.part2_functional_interfaces.functional_support;

import java.util.Objects;
import java.util.function.Predicate;

public class FunctionalInterfaceBridgingExample {
  public static void main(String[] args) {

    // Defining a custom functional interface
    interface LikePredicate<T> {
      boolean test(T value);
    }

    // Lambda expression for custom functional interface
    LikePredicate<String> isNull = Objects::isNull;

    // Trying to assign custom functional interface to Predicate using a cast
    // This will result in a ClassCastException at runtime
    try {
      Predicate<String> wontCompile = (Predicate<String>) isNull;
    } catch (ClassCastException e) {
      System.out.println("Caught ClassCastException: " + e.getMessage());
    }

    // Bridging the gap using a method reference
    Predicate<String> thisIsFine = isNull::test;

    // Demonstrate the bridging in action
    boolean result = thisIsFine.test("example");
    System.out.println("Result using bridged functional interface: " + result);
  }
}
