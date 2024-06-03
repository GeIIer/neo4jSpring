package com.example.neo4jspring.models;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Node("Student")
public class Student {
    @Id
    @GeneratedValue
    private UUID id;
    private String firstname;
    private String lastname;
    private LocalDate birthDate;
    private Double average;
}
