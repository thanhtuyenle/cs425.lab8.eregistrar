package edu.mum.cs.cs425.lab8.eregistrar.model;

import edu.mum.cs.cs425.lab8.eregistrar.model.validators.UniqueStudentNumber;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.context.annotation.SessionScope;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name ="students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;

    @UniqueStudentNumber
    @NotBlank(message = "*Student Number is required")
    @Column(name="studentNumber", nullable = false, unique = true)
    private String studentNumber;

    @NotBlank(message = "*First Name is required")
    @Column(name="firstName", nullable = false)
    private String firstName;

    private String middleName;

    @NotBlank(message = "*Last Name is required")
    @Column(name="lastName", nullable = false)
    private String lastName;

    private Double cgpa;

    @NotNull(message = "*Enrollment Date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate enrollmentDate;

    @NotBlank(message = "Is International is required!")
    @Column(name="isInternational", nullable = false)
    private String isInternational;

    public Student(){

    }

    public Student(@NotBlank(message = "*Student Number is required") String studentNumber, @NotBlank(message = "*First Name is required") String firstName, String middleName, @NotBlank(message = "*Last Name is required") String lastName, Double cgpa, @NotNull(message = "*Enrollment Date is required") LocalDate enrollmentDate, @NotBlank(message = "Is International is required!") String isInternational) {
        this.studentNumber = studentNumber;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.cgpa = cgpa;
        this.enrollmentDate = enrollmentDate;
        this.isInternational = isInternational;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getCgpa() {
        return cgpa;
    }

    public void setCgpa(Double cgpa) {
        this.cgpa = cgpa;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getIsInternational() {
        return isInternational;
    }

    public void setIsInternational(String isInternational) {
        this.isInternational = isInternational;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentNumber='" + studentNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cgpa=" + cgpa +
                ", enrollmentDate=" + enrollmentDate +
                ", isInternational=" + isInternational +
                '}';
    }


}
