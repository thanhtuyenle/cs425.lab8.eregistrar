package edu.mum.cs.cs425.lab8.eregistrar.service;


import edu.mum.cs.cs425.lab8.eregistrar.model.Student;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface StudentService {

    public abstract Iterable<Student> getAllStudents();

    public abstract Page<Student> getAllStudentsPaged(int pageNo);

    public abstract Student saveStudent(Student student);

    public abstract Student getStudentById(Integer studentId);

    public abstract void deleteStudentById(Integer studentId);

    public abstract Optional<Student> findByStudentNumber(String studentNumber);

    public abstract Page<Student> listStudentsByFirstName(String firstName, int pageNo);

}
