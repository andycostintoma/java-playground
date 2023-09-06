package com.andy.futures.customer_service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface CustomerRepository {
    CompletableFuture<Void> saveCustomer(Customer customer);
    CompletableFuture<Optional<Customer>> getCustomer(UUID customerId);
}