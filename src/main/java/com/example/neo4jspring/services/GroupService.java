package com.example.neo4jspring.services;

import com.example.neo4jspring.models.Group;
import com.example.neo4jspring.models.Student;
import com.example.neo4jspring.repositories.GroupRepository;
import com.example.neo4jspring.repositories.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class GroupService implements BaseService<Group> {
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;

    public GroupService(GroupRepository groupRepository, StudentRepository studentRepository) {
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Group getById(UUID id) {
        return groupRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group create(Group node) {
        node.setId(null);
        return groupRepository.save(node);
    }

    @Override
    public Group update(Group node) {
        if (!groupRepository.existsById(node.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return groupRepository.save(node);
    }

    @Override
    public UUID deleteById(UUID id) {
        if (!groupRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        groupRepository.deleteById(id);
        return id;
    }

    @Transactional(transactionManager = "transactionManager")
    public Group addStudent(UUID groupId, Student student) {
        Group group = groupRepository.findById(groupId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        if (student.getId() != null) {
            group.getStudents().add(studentRepository.findById(student.getId()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            ));
            return groupRepository.save(group);
        }
        group.getStudents().add(studentRepository.save(student));
        return groupRepository.save(group);
    }

    public List<Group> getByName(String name) {
        return groupRepository.findAllByNameAndStudents(name);
    }
}
