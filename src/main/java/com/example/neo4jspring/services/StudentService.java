package com.example.neo4jspring.services;

import com.example.neo4jspring.models.Student;
import com.example.neo4jspring.repositories.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService implements BaseService<Student> {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student getById(UUID id) {
        return studentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student create(Student node) {
        node.setId(null);
        return studentRepository.save(node);
    }

    @Override
    public Student update(Student node) {
        if (!studentRepository.existsById(node.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return studentRepository.save(node);
    }

    @Override
    public UUID deleteById(UUID id) {
        if (!studentRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        studentRepository.deleteById(id);
        return id;
    }

    public List<Student> findByAverage(Double average) {
        return studentRepository.findByAverage(average);
    }
}
