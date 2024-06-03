package com.example.neo4jspring.services;

import java.util.List;
import java.util.UUID;

public interface BaseService<T> {
    T getById(UUID id);
    List<T> getAll();
    T create(T node);
    T update(T node);
    UUID deleteById(UUID id);
}
