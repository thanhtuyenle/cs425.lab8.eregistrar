package edu.mum.cs.cs425.lab8.eregistrar.repository;


import edu.mum.cs.cs425.lab8.eregistrar.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    // This interface definition relies on the public abstract methods
    // inherited from the super interface, CrudRepository<T, ID>
    // We may override any or add more methods here, if needed.
    Optional<Student> findStudentByStudentNumber(String studentNumber);

    @Query("Select s from Student s where s.firstName like %:firstName%")
    Page<Student> findStudentByFirstName(String firstName, Pageable pageable);
}
