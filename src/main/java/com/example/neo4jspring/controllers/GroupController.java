package com.example.neo4jspring.controllers;

import com.example.neo4jspring.models.Group;
import com.example.neo4jspring.models.Student;
import com.example.neo4jspring.services.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<Group> getAll() {
        return groupService.getAll();
    }

    @GetMapping("/{id}")
    public Group getById(@PathVariable UUID id) {
        return groupService.getById(id);
    }

    @GetMapping("/name/{name}")
    public List<Group> getById(@PathVariable String name) {
        return groupService.getByName(name);
    }

    @PostMapping
    public Group save(@RequestBody Group group) {
        return groupService.create(group);
    }

    @PutMapping
    public Group update(@RequestBody Group group) {
        return groupService.update(group);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable UUID id) {
        return new ResponseEntity<>(groupService.deleteById(id).toString(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public Group addStudent(@RequestParam UUID groupId, @RequestBody Student student) {
        return groupService.addStudent(groupId, student);
    }
}
