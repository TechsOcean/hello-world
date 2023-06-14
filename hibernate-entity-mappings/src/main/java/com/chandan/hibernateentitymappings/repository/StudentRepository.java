package com.chandan.hibernateentitymappings.repository;

import com.chandan.hibernateentitymappings.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByLastname(String lastname);

    @Query(value = "select * from student s where s.student_id=:studentId", nativeQuery = true)
    Optional<Student> findById(Integer studentId);
}
