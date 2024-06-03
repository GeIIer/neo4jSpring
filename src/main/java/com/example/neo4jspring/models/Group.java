package com.example.neo4jspring.models;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;
import java.util.UUID;

@Data
@Node("Group")
public class Group {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    @Relationship(type = "STUDENT", direction = Relationship.Direction.OUTGOING)
    private List<Student> students;
}
