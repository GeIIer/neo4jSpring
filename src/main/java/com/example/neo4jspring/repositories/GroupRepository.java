package com.example.neo4jspring.repositories;

import com.example.neo4jspring.models.Group;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GroupRepository extends Neo4jRepository<Group, UUID> {
    @Query("MATCH (a:Group {name: $name}) RETURN a")
    List<Group> findAllByName(String name);

    @Query("MATCH (group:`Group` {name: $name}) RETURN " +
            "group{.id, .name, __nodeLabels__: labels(group), __elementId__: id(group), " +
            "Group_STUDENT_Student: [(group)-[:`STUDENT`]->(group_students:`Student`) | " +
            "group_students{.average, .birthDate, .firstname, .id, .lastname, __nodeLabels__: labels(group_students), __elementId__: id(group_students)}]}")
    List<Group> findAllByNameAndStudents(String name);
}
