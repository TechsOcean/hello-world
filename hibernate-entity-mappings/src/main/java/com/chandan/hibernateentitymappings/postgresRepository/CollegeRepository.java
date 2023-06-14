package com.chandan.hibernateentitymappings.postgresRepository;

import com.chandan.hibernateentitymappings.mysql.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeRepository extends JpaRepository<College, Long> {
}
