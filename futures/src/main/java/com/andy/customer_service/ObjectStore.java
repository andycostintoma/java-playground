package com.andy.customer_service;

import java.util.Optional;
import java.util.UUID;

public interface ObjectStore {
    Optional<Object> read(UUID id);
    boolean write(UUID id, Object obj);
}