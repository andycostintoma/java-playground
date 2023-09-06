package com.andy.futures.customer_service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void equals_shouldReturnTrue_ifCustomersAreEqual() {
        Customer customer1 = TestHelpers.generateCustomer();
        Customer customer2 = new Customer(customer1.getId(), customer1.getFirstName(), customer1.getLastName(), customer1.getAddress(), customer1.getPhoneNumber());

        assertEquals(customer1, customer2);
    }

    @Test
    void equals_shouldReturnFalse_ifCustomersAreNotEqual() {
        Customer customer1 = TestHelpers.generateCustomer();
        Customer customer2 = TestHelpers.generateCustomer();

        assertNotEquals(customer1, customer2);
    }

}
