package com.example.neo4jspring.controllers;

import com.example.neo4jspring.models.Student;
import com.example.neo4jspring.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable UUID id) {
        return studentService.getById(id);
    }

    @GetMapping("/average")
    public List<Student> getByAverage(@RequestParam Double average) {
        return studentService.findByAverage(average);
    }

    @PostMapping
    public Student save(@RequestBody Student student) {
        return studentService.create(student);
    }

    @PutMapping
    public Student update(@RequestBody Student student) {
        return studentService.update(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable UUID id) {
        return new ResponseEntity<>(studentService.deleteById(id).toString(), HttpStatus.OK);
    }
}
