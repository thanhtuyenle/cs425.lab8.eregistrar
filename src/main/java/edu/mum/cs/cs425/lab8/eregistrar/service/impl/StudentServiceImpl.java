package edu.mum.cs.cs425.lab8.eregistrar.service.impl;


import edu.mum.cs.cs425.lab8.eregistrar.model.Student;
import edu.mum.cs.cs425.lab8.eregistrar.repository.StudentRepository;
import edu.mum.cs.cs425.lab8.eregistrar.service.StudentService;
import org.apache.el.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

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

//    @Override
//    public Page<Student> searchStudents(String firstName, int pageNo) {
//        if (firstName != null && firstName.isEmpty() == false) {
//            return repository.findStudentByFirstName(firstName, PageRequest.of(pageNo, 3, Sort.by("firstName")));
//        } else {
//            return getAllStudentsPaged(pageNo);
//        }
//    }

    @Override
    public Page<Student> searchStudents(String searchString, int pageNo) {

        if(containsDecimalPoint(searchString) && isCGPA(searchString)) {
            return repository.findAllByCgpaEquals(Double.parseDouble(searchString), PageRequest.of(pageNo, 3, Sort.by("studentNumber")));

        } else if(isDate(searchString)) {
            return repository.findAllByEnrollmentDate(LocalDate.parse(searchString, DateTimeFormatter.ISO_DATE), PageRequest.of(pageNo, 3, Sort.by("studentNumber")));
        } else {
            return repository.findAllByStudentNumberContainingOrFirstNameContainingOrMiddleNameContainingOrLastNameContaining(searchString, searchString, searchString, searchString, PageRequest.of(pageNo, 3, Sort.by("studentNumber")));
        }
    }

    private boolean isCGPA(String searchString) {
        boolean isParseableAsMoney = false;
        try {

            Double.parseDouble(searchString);
            isParseableAsMoney = true;
        } catch(Exception ex) {
            if(ex instanceof ParseException) {
                isParseableAsMoney = false;


            }
        }
        return isParseableAsMoney;
    }

    private boolean isDate(String searchString) {
        boolean isParseableAsDate = false;
        try {
            LocalDate.parse(searchString, DateTimeFormatter.ISO_DATE);
            isParseableAsDate = true;
        } catch(Exception ex) {
            if(ex instanceof DateTimeParseException) {
                isParseableAsDate = false;
            }
        }
        return isParseableAsDate;
    }

    private boolean containsDecimalPoint(String searchString) {
        return searchString.contains(".");
    }

}
