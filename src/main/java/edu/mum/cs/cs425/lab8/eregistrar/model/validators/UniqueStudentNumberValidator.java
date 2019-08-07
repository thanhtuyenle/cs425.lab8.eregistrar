package edu.mum.cs.cs425.lab8.eregistrar.model.validators;

import edu.mum.cs.cs425.lab8.eregistrar.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueStudentNumberValidator implements ConstraintValidator<UniqueStudentNumber, String> {

    private StudentService studentService;

    public UniqueStudentNumberValidator() {
    }

    @Autowired
    public UniqueStudentNumberValidator(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void initialize(UniqueStudentNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String studentNumber, ConstraintValidatorContext context) {
        boolean valid;
        if (studentService != null) {
            valid = (studentNumber != null && !studentService.findByStudentNumber(studentNumber).isPresent());
        } else {
            valid = true;
        }
        return valid;
    }
}
