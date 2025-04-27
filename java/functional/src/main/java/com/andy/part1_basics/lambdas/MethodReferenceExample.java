package com.andy.part1_basics.lambdas;

import java.util.Arrays;
import java.util.List;


public class MethodReferenceExample {

    static class Person {
        private final String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static boolean hasLongName(Person person) {
            return person.getName().length() >= 8;
        }

        public boolean hasNameLength(int length) {
            return name.length() == length;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Alice"),
                new Person("Bob"),
                new Person("Alexander")

        );

        // Static method reference
        List<Person> adults = people.stream()
                .filter(Person::hasLongName)
                .toList();
        System.out.println("Adults: " + adults);

        // Bound non-static method reference
        List<Person> namesWithLength = people.stream()
                .filter(person -> person.hasNameLength(5))
                .toList();
        System.out.println("Names with length 5: " + namesWithLength);

        // Unbound non-static method reference
        List<String> transformedNames = people.stream()
                .map(Person::getName)
                .toList();
        System.out.println("Names: " + transformedNames);

        // Constructor reference
        List<Person> newPeople = Arrays.stream(new String[]{"Emily", "Frank", "Grace"})
                .map(Person::new)
                .toList();
        System.out.println("New People: " + newPeople);
    }
}
