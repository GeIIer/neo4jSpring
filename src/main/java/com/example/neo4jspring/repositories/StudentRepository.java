package com.example.neo4jspring.repositories;

import com.example.neo4jspring.models.Student;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends Neo4jRepository<Student, UUID> {
    @Query("MATCH (a:Student WHERE a.average > $average) RETURN a")
    List<Student> findByAverage(Double average);
}
