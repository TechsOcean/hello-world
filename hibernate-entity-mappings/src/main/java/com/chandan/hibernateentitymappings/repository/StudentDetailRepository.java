package com.chandan.hibernateentitymappings.repository;

import com.chandan.hibernateentitymappings.entities.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDetailRepository extends JpaRepository<StudentDetails, Long> {
}
