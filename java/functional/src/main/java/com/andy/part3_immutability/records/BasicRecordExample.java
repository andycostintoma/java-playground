package com.andy.part3_immutability.records;


public class BasicRecordExample {

    record Person(String firstName, String lastName, int age) {
        // No additional methods or constructor needed
    }

    public static void main(String[] args) {
        // Creating a Person record instance
        Person person = new Person("John", "Doe", 30);

        // Accessing components using accessor methods
        String firstName = person.firstName();
        String lastName = person.lastName();
        int age = person.age();

        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Age: " + age);
    }
}
