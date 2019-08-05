package edu.mum.cs.cs425.lab8.eregistrar.service.impl;


import edu.mum.cs.cs425.lab8.eregistrar.model.Student;
import edu.mum.cs.cs425.lab8.eregistrar.repository.StudentRepository;
import edu.mum.cs.cs425.lab8.eregistrar.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Filter;
import java.util.stream.Collectors;

//@Service(value = "MainBookService")
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    @Override
    public Iterable<Student> getAllStudents() {
//        return ((List<Book>)repository.findAll())
//                .stream()
//                .sorted(Comparator.comparing(Book::getTitle))
//                .collect(Collectors.toList());
        return repository.findAll(Sort.by("studentNumber"));
    }

    @Override
    public Page<Student> getAllStudentsPaged(int pageNo) {
        return repository.findAll(PageRequest.of(pageNo, 3, Sort.by("studentNumber")));
    }

    @Override
    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    @Override
    public Student getStudentById(Integer studentId) {
        return repository.findById(studentId).orElse(null);
    }

    @Override
    public void deleteStudentById(Integer studentId) {
        repository.deleteById(studentId);
    }

    @Override
    public Optional<Student> findByStudentNumber(String studentNumber) {
        return repository.findStudentByStudentNumber(studentNumber);
    }

    @Override
    public Page<Student> listStudentsByFirstName(String firstName, int pageNo) {
        if (firstName != null && firstName.isEmpty() == false) {
            return repository.findStudentByFirstName(firstName, PageRequest.of(pageNo, 3, Sort.by("firstName")));
        } else {
            return getAllStudentsPaged(pageNo);
        }
    }
}
