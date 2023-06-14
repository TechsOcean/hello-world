package com.chandan.hibernateentitymappings.repository;

import com.chandan.hibernateentitymappings.entities.Student;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class StudentDemoRepository implements IStudentRepository<Student> {

    private final HashMap<Long, Student> studentHashMap;

    public StudentDemoRepository(HashMap<Long, Student> studentHashMap) {
        this.studentHashMap = studentHashMap;
    }

    @Override
    public void save(Student student) {
        studentHashMap.put((long) student.getStudent_id(), student);
    }

    @Override
    public Student findStudentById(Long id) {
        return studentHashMap.get(id);
    }
}
